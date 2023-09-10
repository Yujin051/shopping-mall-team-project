package com.project.shop.controller.admin;

import com.project.shop.entity.Item;
import com.project.shop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminItemsController {

	@Autowired
	private ItemService itemService;
	@GetMapping(value = "/ItemList")
	public String adminItemsList() {
		return "/admin/admin_ItemList";
	}

	@GetMapping("/newItem")
	public String newItem() {
		return "/admin/newItem";
	}

	@PostMapping("/newItemReg")
	public String newItemReg(Item item) {
		itemService.newItem(item);

		return "redirect:/admin/ItemList";
	}
	
	@GetMapping("/modifyItem")
	public String modifyItem() {
		return "/admin/modifyItem";
	}
}
