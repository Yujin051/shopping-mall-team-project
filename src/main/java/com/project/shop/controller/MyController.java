package com.project.shop.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.shop.service.OrderDetailService;

import com.project.shop.entity.Member;
import com.project.shop.repository.MemberRepository;
import com.project.shop.service.MemberService;

import lombok.RequiredArgsConstructor;
// 비밀번호 확인 관련 컨트롤러입니다.
@RequiredArgsConstructor
@RequestMapping("/my")
@Controller
public class MyController {

    private final OrderDetailService orderDetailService;


	private final MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@Autowired
	private  PasswordEncoder passwordEncoder;

    // 주문/리뷰 컨트롤러 추가
    // 추후 필요하면 분리할 것
    @GetMapping("/orderDetail")
    public String orderDetail(Model model) {
      System.out.println(orderDetailService.ordersList());
 	  model.addAttribute("list", orderDetailService.ordersList());
      return "my/orderDetail";
    }

	// 리뷰 별도 구현을 위해 분리합니다. >> ReviewController
//    @GetMapping("/review/")
//    public String review() {
//      return "my/review";
//    }
//
    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/mypage")
//    public String mypage(Principal principal, Model model) {
//    	 String userid = principal.getName();
//         Member member = memberRepository.findByEmail(userid);
//         model.addAttribute("member", member);
//         return "my/mypage"; // myProfile.html 또는 해당 뷰 이름으로 반환
//    }
    
//    @PostMapping("/mypage")
//    public String modify(Principal principal, Model model, Member member) {
//
//    }
    // 회원정보 수정 view 이동
    @GetMapping("/mypage1")
    public String mypage2(Model model, Authentication auth) {
    	UserDetails userDetails = (UserDetails) auth.getPrincipal();
    	Member member = memberRepository.findByEmail(userDetails.getUsername());
    	model.addAttribute("member", member);

    	return "my/mypage";
    }
    //회원정보 수정하기
	@PostMapping("/modify")
	public String noticeUpdate(Member member, Principal principal, Model model) {
		Member memberT = memberService.memberView(principal.getName());
		memberT.setPhonenum(member.getPhonenum());
		memberT.setPassword(passwordEncoder.encode(member.getPassword()));
		memberService.updateMember(memberT);
		return "redirect:/";
	}
}
