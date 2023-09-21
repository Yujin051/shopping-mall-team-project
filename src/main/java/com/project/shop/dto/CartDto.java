package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartDto {
    // 해당 상품 id
    private Long itemId;
    // 장바구니 상품 id
    private Long cartItemId;
    // 장바구니 상품 이름
    private String itemName;
    // 장바구니 상품 가격
    private int price;
    // 장바구니 상품 개수
    private int count;
    // 장바구니 상품 이미지 이름
    private String imgSaved;
}
