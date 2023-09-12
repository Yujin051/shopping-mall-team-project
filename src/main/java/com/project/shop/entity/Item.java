package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id; // 상품코드

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName; //상품명

    @Column(name = "item_price", nullable = false)
    private int itemPrice;  //가격

    @Column(name = "item_qty", nullable = false)
    private int itemQty;  //재고

    //@Lob // 넣었다가 굳이 넣을필요 없어서 뺌
    @Column(name = "item_content", nullable = false)
    private String itemContent;  //상품 상세설명

    @Column(name = "main_cate", nullable = false)
    private String mainCate;  // 대분류 카테고리

    @Column(name = "sub_cate")
    private String subCate;  // 중분류 카테고리

    @Builder
    public Item(String itemName, int itemPrice, int itemQty,
                String itemContent, String mainCate, String subCate) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemContent = itemContent;
        this.mainCate = mainCate;
        this.subCate = subCate;
    }

    @Builder
    public Item(Long id, String itemName, int itemPrice, int itemQty,
                String itemContent, String mainCate, String subCate) {
        this.id = id;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.itemContent = itemContent;
        this.mainCate = mainCate;
        this.subCate = subCate;
    }

    // 상품 주문 시 재고 감소와 재고가 없을 때 오류 담당 메소드
    public void removeQty(int itemQty) {
        int restQty = this.itemQty - itemQty;
        if( restQty < 0 ) {
            // 사용자 정의 에러 발생
        }
        this.itemQty = restQty;
    }
}
