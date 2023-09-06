package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@GetMapping("/new")
	public String noticeNewArticle() {
		return "notice/noticeNewArticle";		
	}
	
	// 임시로 1 매핑, {noticeid} 로 게시글에 따라 파라미터 받아와서
		// 해당하는 게시글 읽도록 수정할 것
	@GetMapping("/1")
	public String noticeReadArticle() {
		return "notice/noticeReadArticle";
	}
}
