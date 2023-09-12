package com.project.shop.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.service.OrderDetailService;

import lombok.RequiredArgsConstructor;
// 비밀번호 확인 관련 컨트롤러입니다.
@RequiredArgsConstructor
@RequestMapping("/my")
@Controller
public class MyController {
    
    private final OrderDetailService orderDetailService;
	
    // 주문/리뷰 컨트롤러 추가
    // 추후 필요하면 분리할 것
    @GetMapping("/orderDetail")
    public String orderDetail(Model model) {
      System.out.println(orderDetailService.ordersList());
 	  model.addAttribute("list", orderDetailService.ordersList());	
      return "my/orderDetail";
    }

    @GetMapping("/review")
    public String review() {
      return "my/review";
    }
    
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
    	return "/my/MemberUpdate";
    }
    
    @PostMapping(value = "/update")
    public String memberUpdate(Model model) {
    	return "";
    }
}
