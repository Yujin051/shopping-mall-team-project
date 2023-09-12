package com.project.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shop.entity.Orders;
import com.project.shop.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

	private final OrdersRepository ordersRepository;
	
	public List<Orders> ordersList() {
        return ordersRepository.findAll();
    }
}
