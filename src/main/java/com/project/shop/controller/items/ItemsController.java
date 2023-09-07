package com.project.shop.controller.items;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsController {

	@GetMapping(value = "/items")
	   public String items() {
		   return "items/items";
	   }
}
