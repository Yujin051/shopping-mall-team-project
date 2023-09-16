package com.project.shop.service;

import java.util.List;

import com.project.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import com.project.shop.dto.OrdersManageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
	
	private final OrderRepository orderRepository;
	
	public List<OrdersManageDto> ordersList() {
		return orderRepository.findOrdersManageDto();
	}

}
