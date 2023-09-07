package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminItemsController {

	@GetMapping(value = "/admin/ItemList")
	   public String adminItemsList() {
		   return "admin/admin_ItemList";
	   }
}
