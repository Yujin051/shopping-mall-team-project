package com.project.shop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;

@Controller
public class OrderController {
	@GetMapping("/test/test")
	// 로그인한 회원 정보에 따른 주문 성공여부 처리인데 잘 모르겠습니다.
	public @ResponseBody ResponseEntity<String> order(@RequestBody @Valid OrderDto orderdto, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
			}
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		String username = principal.getName();
		System.out.println(username);
		return new ResponseEntity<String>(username + "님의 주문이 성공적으로 처리되었습니다.", HttpStatus.OK);
	
	}
}
