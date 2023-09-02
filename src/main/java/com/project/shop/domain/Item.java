package com.project.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	    @Column(name = "cate_code", nullable = false)
	    private int cateCode;  // 카테고리 코드

	    @Builder
	    public Item(String itemName, int itemPrice, int itemQty, String itemContent, int cateCode) {
	        this.itemName = itemName;
	        this.itemPrice = itemPrice;
	        this.itemQty = itemQty;
	        this.itemContent = itemContent;
	        this.cateCode = cateCode;
	    }
}
