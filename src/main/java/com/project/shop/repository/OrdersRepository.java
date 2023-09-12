package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    
}