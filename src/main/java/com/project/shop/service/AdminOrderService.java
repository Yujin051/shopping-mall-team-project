package com.project.shop.service;

import java.util.List;

import com.project.shop.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.shop.dto.OrdersManageDto;
import com.project.shop.orders.Order;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

	private final OrderRepository orderRepository;
	
	public Page<OrdersManageDto> ordersList(Pageable pageable) {
        return orderRepository.findOrdersManageDto(pageable);
    }
	
	public void update(Order orders) {
		orderRepository.save(orders);
	}
	
	public Order OrdersView(Long orderId) {
		return orderRepository.findById(orderId).get();
	}
}
