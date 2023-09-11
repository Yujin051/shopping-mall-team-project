package com.project.shop.dto;

import com.project.shop.entity.Item;
import com.project.shop.entity.ItemImg;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {
    private Long id; // 상품코드

    private String itemName; //상품명

    private int itemPrice;  //가격

    private int itemQty;  //재고

    private String itemContent;  //상품 상세설명

    private String mainCate;  // 카테고리 대분류

    private String subCate;	 // 카테고리 중분류

    private ItemImg itemImg;


    public ItemDto of(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemQty(item.getItemQty())
                .itemContent(item.getItemContent())
                .mainCate(item.getMainCate())
                .subCate(item.getSubCate())
                .itemImg(item.getItemImg())
                .build();

    }
}
