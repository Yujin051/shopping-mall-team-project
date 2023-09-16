package com.project.shop.service;

import java.util.List;

import com.project.shop.entity.Member;
import com.project.shop.repository.MemberRepository;
import com.project.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;

import com.project.shop.dto.OrdersManageDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;

	public List<OrdersManageDto> ordersList() {
		return orderRepository.findOrdersManageDto();
	}

	public List<OrdersManageDto> myOrdersList(String email) {
		Long memberId = memberRepository.findByEmail(email).getId();
		return orderRepository.findOrderMyManageDto(memberId);
	}
}
