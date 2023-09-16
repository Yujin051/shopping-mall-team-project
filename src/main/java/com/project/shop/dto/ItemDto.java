package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    // 상품 정보를 불러오기 위한 DTO 객체 생성


    private Long id; // 상품코드

    private String itemName; //상품명

    private int itemPrice;  //가격

    private int itemQty;  //재고

    private String itemContent;  //상품 상세설명

    private String mainCate;  // 카테고리 대분류

    private String subCate;	 // 카테고리 중분류
}
