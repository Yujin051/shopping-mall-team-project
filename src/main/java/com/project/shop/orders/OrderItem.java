package com.project.shop.orders;

import com.project.shop.entity.Item;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "order_item")
@Entity
public class OrderItem {
    // OrderItem 엔티티는 현재 사용하지 않음
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;

    private int orderPrice;  //주문가격

    private int count;  //수량

//    @Builder
//    public OrderItem(Item item, Order order, int orderPrice, int count) {
//        this.item = item;
//        this.order = order;
//        this.orderPrice = orderPrice;
//        this.count = count;
//    }
//
//    // 주문 수량과 상품 정보를 가지고 있는 OrderItem 객체 생성 메소드
//    public static OrderItem createOrderItem(Item item, int count) {
//        OrderItem orderItem = new OrderItem();
//        orderItem.setItem(item);
//        orderItem.setCount(count);
//        orderItem.setOrderPrice(item.getItemPrice());
//
//        item.removeQty(count);
//
//        return orderItem;
//    }
//
//    // 총 주문 금액 알아내기
//    public int getTotalPrice() {
//        return orderPrice * count;
//    }
}