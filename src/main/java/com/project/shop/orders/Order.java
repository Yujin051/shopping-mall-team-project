package com.project.shop.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.project.shop.constant.OrderStatus;
import com.project.shop.entity.Item;
import com.project.shop.entity.Member;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "order_price")
    private int orderPrice;

    @Column(name = "order_count")
    private int orderCount;

    // private LocalDateTime orderDate;    //주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;    //주문상태
    
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> orderItems = new ArrayList<>();



    // 주문 객체를 생성(멤버와 주문 아이템 테이블을 이용하여 생성)
    public static Order createOrder(Member member, Item item) {
        Order order = new Order();
        order.setItem(item);
        order.setMember(member);
        return order;
    }

    public int totalPrice() {
        return orderPrice * orderCount;
    }
}
