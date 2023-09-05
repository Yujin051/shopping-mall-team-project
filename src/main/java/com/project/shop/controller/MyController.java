package com.project.shop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.service.MemberService;

import lombok.RequiredArgsConstructor;
// 비밀번호 확인 관련 컨트롤러입니다.
@RequiredArgsConstructor
@RequestMapping("/my")
@Controller
public class MyController {
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	

    
    @GetMapping(value ="/pwcheck")
    public String loginMember() {
    	return "/my/pwCheck";
    }
    
    @GetMapping(value = "/pwcheck/error")
    public String loginError(Model model) {
    	model.addAttribute("loginErrorMsg", "비밀번호가 틀렸습니다.");
    	return "/my/pwCheck";
    }
    
    @GetMapping(value = "/update")
    public String memberUpdate() {
    	return "my/memberupdate";
    }
}

