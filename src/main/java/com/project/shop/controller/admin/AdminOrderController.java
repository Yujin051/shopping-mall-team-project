package com.project.shop.controller.admin;

import com.project.shop.orders.Order;
import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.shop.service.AdminOrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminOrderController {
   	Logger logger = LoggerFactory.getLogger(AdminOrderController.class);

    private final AdminOrderService adminOrderService;

    @GetMapping(value = "/admin/adminOrder")
    public String adminOrder(Model model) {
        model.addAttribute("list", adminOrderService.ordersList());
		logger.info(adminOrderService.ordersList().toString());
        return "admin/admin_order";
    }

    @PostMapping("/admin/update/{id}")
    public String ordersUpdate(@PathVariable("id") Long orderId, Order orders, Model model, @RequestParam("select") String select ) {
        Order ordersT = adminOrderService.OrdersView(orderId);
        ordersT.setOrderStatus(select);
        model.addAttribute("message", "등록되었습니다.");
        model.addAttribute("SearchUrl", "/admin/adminOrder");

        adminOrderService.update(ordersT);

        return "admin/AdminMessage";

    }
}
