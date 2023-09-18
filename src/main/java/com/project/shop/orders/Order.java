package com.project.shop.orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.shop.constant.OrderStatus;
import com.project.shop.entity.Item;
import com.project.shop.entity.Member;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "orders")
@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date orderDate;    //주문일

    //@Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private String orderStatus;    //주문상태
    
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<OrderItem> orderItems = new ArrayList<>();



    // 주문 객체를 생성(멤버와 주문 아이템 테이블을 이용하여 생성)
    public static Order createOrder(Member member, Item item, int orderPrice, int orderCount) {
        Order order = new Order();
        order.setItem(item);
        order.setMember(member);
        order.setOrderPrice(orderPrice);
        order.setOrderCount(orderCount);

        // 주문 객체 생성 시 주문한 수 만큼 상품 재고 감소 메소드
        item.removeQty(orderCount);

        return order;
    }

    // 현재 주문 상품 테이블 병합으로 인해 사용하지 않음
    // @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<OrderItem> orderItems = new ArrayList<>();
}
