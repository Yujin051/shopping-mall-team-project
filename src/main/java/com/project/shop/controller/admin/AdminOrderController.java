package com.project.shop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.shop.entity.Notice;
import com.project.shop.entity.Orders;
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
   
   @PostMapping("/update/{id}")
   public String ordersUpdate(@PathVariable("id")Long orderId, Orders orders, Model model) {
	    Orders ordersT = adminOrderService.OrdersView(orderId);
	    ordersT.setOrderStatus(orders.getOrderStatus());
		model.addAttribute("message", "등록되었습니다.");
		model.addAttribute("SearchUrl", "/admin/admin_order");
		
		adminOrderService.update(ordersT);
		
		return "admin/AdminMessage";
	   
   }
}
