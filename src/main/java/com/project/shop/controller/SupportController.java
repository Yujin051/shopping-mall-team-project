package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/support")
@Controller
public class SupportController {
	
	@GetMapping(value = "direction")
	public String direction() {
		return "/support/direction";
	}
}
