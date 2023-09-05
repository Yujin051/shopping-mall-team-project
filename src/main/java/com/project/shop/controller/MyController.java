package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

	@GetMapping("/my/orderDetail")
	public String orderDetail() {
		return "my/orderDetail";
	}
}
