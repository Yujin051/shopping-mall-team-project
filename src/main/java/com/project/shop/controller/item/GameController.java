package com.project.shop.controller.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class GameController {
	
	@GetMapping("/game")
	public String gameMain() {
		return "/items/game/root";
	}
}
