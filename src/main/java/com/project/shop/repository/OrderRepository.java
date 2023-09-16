package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}