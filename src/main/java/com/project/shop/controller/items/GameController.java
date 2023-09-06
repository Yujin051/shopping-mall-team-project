package com.project.shop.controller.items;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class GameController {
	
	@GetMapping("/game")
	public String gameMain() {
		return "/items/game/root";
	}
}
