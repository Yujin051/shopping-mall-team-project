package com.project.shop.controller.admin;

import com.project.shop.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.repository.MemberRepository;
import com.project.shop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminMemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/memberlist")
	public String memberList(@PageableDefault(size = 15) Pageable pageable, Model model) {
		System.out.println(memberService.memberList());

		Page<Member> member = memberService.getMemberList(pageable);

		int startPage = Math.max(1, member.getPageable().getPageNumber() - 4);
		int endPage = Math.min(member.getPageable().getPageNumber()+4, member.getTotalPages());

		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", member);
		//model.addAttribute("list", memberService.memberList());
		return "admin/admin_member";
	}
	
	@GetMapping("/deletemember")
	public String noticeDelete(Long id, Model model) {
		memberService.memberDelete(id);
		
		model.addAttribute("message", "회원 탈퇴가 완료되었습니다.");
		model.addAttribute("SearchUrl", "/admin/memberlist");
		
		return "admin/message";
	}
}
