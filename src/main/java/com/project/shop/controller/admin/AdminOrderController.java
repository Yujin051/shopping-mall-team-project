package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.shop.service.AdminOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminOrderController {
   
	private final AdminOrderService adminOrderService;
	
   @GetMapping(value = "/admin/adminOrder")
   public String adminOrder(Model model) {
	   System.out.println(adminOrderService.ordersList());
	   model.addAttribute("list", adminOrderService.ordersList());
	   return "admin/admin_order";
   }
}
