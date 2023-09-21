package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "cart_item")
@Entity
public class CartItem {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
    
    private int count;

    // 생성자로 장바구니에 담을 새로운 cartItem 객체 만들기
    @Builder
    public CartItem(Cart cart, Item item, int count) {
        this.cart = cart;
        this.item = item;
        this.count = count;
    }

    // 같은 상품을 중복해서 담았을 경우 수량 추가
    public void addCount(int count) {
        this.count += count;
    }

    // 장바구니 상품 개수 업데이트 로직
    public void updateCount(int count) {
        this.count = count;
    }
}