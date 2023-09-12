package com.project.shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
	private int orderItemId; // 주문상품id
	   
	@Column(name = "item_id" ,nullable = false)
	private int itemId; // 상품id
	   
	@Column(name = "order_id" ,nullable = false)
	private int orderId; // 주문번호
	   
	@Column(name = "order_price" ,nullable = false)
	private int orderPrice; // 주문가격
	   
	@Column(name = "count" ,nullable = false)
	private int count; // 주문수량
	   
	@Builder
	public OrderItem(int itemId, int orderId, int orderPrice, int count) {
		this.itemId = itemId;
		this.orderId = orderId;
		this.orderPrice = orderPrice;
		this.count = count;
	}
}

