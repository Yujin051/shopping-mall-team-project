package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminItemsController {

	@GetMapping(value = "/ItemList")
	public String adminItemsList() {
		return "/admin/admin_ItemList";
	}
	@GetMapping("/newItem")
	public String newItem() {
		return "/admin/newItem";
	}
	
	@GetMapping("/modifyItem")
	public String modifyItem() {
		return "/admin/modifyItem";
	}
}
