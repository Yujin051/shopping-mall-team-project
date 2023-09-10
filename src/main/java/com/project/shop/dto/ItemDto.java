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

    private String title;

    private int price;

    private String content;

    private String mainCate;

    private String subCate;

}
