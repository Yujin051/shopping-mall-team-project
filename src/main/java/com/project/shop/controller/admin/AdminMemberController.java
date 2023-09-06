package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminMemberController {

	@GetMapping(value = "/admin/member")
	   public String adminItemsList() {
		   return "admin/admin_member";
	   }
}
