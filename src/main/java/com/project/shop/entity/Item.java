package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Table(name = "item")
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
	@Column(name = "item_content")
	private String itemContent;  //상품 상세설명

	@Column(name = "main_cate", nullable = false)
	private String mainCate;  // 카테고리 대분류

	@Column(name = "sub_cate")
	private String subCate;	 // 카테고리 중분류

	@Column(name = "img_original", nullable = false)
	private String imgOriginal; // 원본 이미지 이름

	@Column(name = "img_saved", nullable = false)
	private String imgSaved; // db에 저장될 이미지 이름

	@Builder
	public Item(String itemName, int itemPrice, int itemQty, String itemContent, String mainCate, String subCate, String imgOriginal, String imgSaved) {
	    this.itemName = itemName;
	    this.itemPrice = itemPrice;
	    this.itemQty = itemQty;
	    this.itemContent = itemContent;
	    this.mainCate = mainCate;
		this.subCate = subCate;
		this.imgOriginal = imgOriginal;
		this.imgSaved = imgSaved;
	   }
}
