package com.project.shop.dto;

import com.querydsl.core.annotations.QueryProjection;
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

    // DTO로 바로 변환시키기 위한 어노테이션
    // 엔티티로 받아도 괜찮지 않을까?
    @QueryProjection
    public ItemDto(Long id, String itemName, String itemContent,
                   String mainCate, String subCate) {
        this.id = id;
        this.itemName = itemName;
        this.itemContent = itemContent;
        this.mainCate = mainCate;
        this.subCate = subCate;
    }

}
