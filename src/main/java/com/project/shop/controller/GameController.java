package com.project.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/items/game")
@Controller
public class GameController {
    
	@GetMapping(value ="/root")
    public String gameroot() {
    	return "/items/game/root";
    }
}
