package com.project.shop.service;

import java.util.List;

import com.project.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import com.project.shop.dto.OrdersManageDto;
import com.project.shop.orders.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

	private final OrderRepository orderRepository;
	
	public List<OrdersManageDto> ordersList() {
        return orderRepository.findOrdersManageDto();
    }
	
	public void update(Order orders) {
		orderRepository.save(orders);
	}
	
	public Order OrdersView(Long orderId) {
		return orderRepository.findById(orderId).get();
	}
}
