package com.project.shop.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId; // 주문번호
    
    @Column(name = "member_id" ,nullable = false)
    private String memberId; // 회원아이디

    @Column(name = "item_id" ,nullable = false)
    private Long itemId; // 상품id
   
    @Column(name = "order_status" ,nullable = false)
    private String orderStatus; // 처리상태
   
    @Column(name = "order_date" ,nullable = false)
    @CreatedDate
    private LocalDate orderDate; // 주문일
    
    @Column(name = "order_price" ,nullable = false)
    private int orderPrice; // 주문가격
    
    @Column(name = "order_count" ,nullable = false)
    private int orderCount; // 주문수량
   
    @Builder
    public Orders(String memberId, long itemId, String orderStatus, LocalDate orderDate,
    		      int orderPrice, int orderCount) {
	    this.memberId = memberId;
    	this.itemId = itemId;
	    this.orderStatus = orderStatus;
	    this.orderDate = orderDate;
	    this.orderPrice = orderPrice;
	    this.orderCount = orderCount;
    }
}
