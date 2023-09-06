package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class adminOrderController {
   @GetMapping(value = "/admin/adminOrder")
   public String adminOrder() {
	   return "admin/admin_order";
   }
}
