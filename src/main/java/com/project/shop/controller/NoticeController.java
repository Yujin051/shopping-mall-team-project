package com.project.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.entity.Notice;
import com.project.shop.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	

	@Autowired
	private NoticeService noticeService;

   // 전체 공지사항 목록 컨트롤러. 기존 컨트롤러에 통합합니다.
   @GetMapping(value = "/root")
   public String noticeList(Model model, Principal principal) {
	   System.out.println(noticeService.noticeList());
	   model.addAttribute("list", noticeService.noticeList());
	   String username = principal.getName();
	   System.out.println("유저네임은" + username);

	   return "notice/root";
   }
  
  // 공지사항 작성 컨트롤러
   // 공지사항 작성하는 페이지로 이동
	@GetMapping("/new")
	public String noticeNewArticle() {
		return "notice/noticeNewArticle";		
	}
	
	@PostMapping("/write")
	public String noticeWrite(Notice notice) {
		noticeService.write(notice);

		return "redirect:/notice/root";
	}

	// notice/root 의 글제목 클릭 시 상세보기
	@GetMapping("/view")
	public String noticeView(Model model, Long id) {
		model.addAttribute("notice",noticeService.noticeView(id));
		return "notice/noticeReadArticle";
	}

	// notice 글 삭제
	@GetMapping("/delete")
	public String noticeDelete(Long id, Model model) {
		noticeService.noticeDelete(id);

		model.addAttribute("message", "게시글이 삭제되었습니다.");
		model.addAttribute("SearchUrl", "/notice/root");

		return "notice/message";
	}

	// notice 글 수정(GET 방식 활용해서 뷰로 이동)
	@GetMapping("/modify/{id}")
	public String noticeModify(@PathVariable("id")Long id, Model model) {
		model.addAttribute("notice", noticeService.noticeView(id));

		return "notice/noticeModifyArticle";
	}

	// notice 글 수정(POST 방식 활용해서 DB 값 수정)
	@PostMapping("/update/{id}")
	public String noticeUpdate(@PathVariable("id")Long id, Notice notice, Model model) {
		Notice noticeT = noticeService.noticeView(id);
		noticeT.setTitle(notice.getTitle());
		noticeT.setContent(notice.getContent());
		noticeT.setDate(notice.getDate());
		model.addAttribute("message", "게시글이 수정되었습니다.");
		model.addAttribute("SearchUrl", "/notice/root");

		noticeService.write(noticeT);

		return "notice/Message";


	}
}
