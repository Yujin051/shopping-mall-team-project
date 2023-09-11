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

    private Long id;

    private String itemName;

    private int itemPrice;

    private String itemContent;

    private String mainCate;

    private String subCate;

}
