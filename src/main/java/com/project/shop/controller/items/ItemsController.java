package com.project.shop.controller.items;

import com.project.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemsController {

	@Autowired
	private ItemService itemService;

	@GetMapping(value = "/items")
	   public String items(Model model, Long id) {
			model.addAttribute("items", itemService.itemView(id));
		   return "items/root";
	   }
}
