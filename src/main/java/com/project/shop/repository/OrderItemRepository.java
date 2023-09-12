package com.project.shop.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Orders;
import com.project.shop.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List <OrderItem> findByOrderItemId(int orderItemId);
	    
	List <OrderItem> findByItemId(int itemId);
	    
	List <OrderItem> findByOrderId(int orderId);
	    
	List <OrderItem> findByOrderPrice(int orderPrice);
	
	List <OrderItem> findByCount(int count);	
}