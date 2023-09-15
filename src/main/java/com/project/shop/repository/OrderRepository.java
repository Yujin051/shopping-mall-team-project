package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.orders.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // 회원과 상품 정보가 모두 일치하는 주문 목록 찾기
    Order findByItemIdAndMemberId(Long itemId, Long memberId);
}