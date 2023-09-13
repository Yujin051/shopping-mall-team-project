package com.project.shop.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.entity.Member;
import com.project.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
// 비밀번호 확인 관련 컨트롤러입니다.
@RequiredArgsConstructor
@RequestMapping("/my")
@Controller
public class MyController {
	
	private final MemberRepository memberRepository;
	

    // 주문/리뷰 컨트롤러 추가
    // 추후 필요하면 분리할 것
    @GetMapping("/orderDetail")
    public String orderDetail() {
      return "my/orderDetail";
    }

    @GetMapping("/review")
    public String review() {
      return "my/review";
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypage(Principal principal, Model model) {
    	 String userid = principal.getName();
         Member member = memberRepository.findByEmail(userid);
         model.addAttribute("member", member);
         return "my/mypage"; // myProfile.html 또는 해당 뷰 이름으로 반환
    }
    
    //@PostMapping("/modify")
    
    
    
}
