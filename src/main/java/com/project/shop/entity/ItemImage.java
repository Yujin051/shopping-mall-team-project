package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_img")
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(name = "img_original")
    private String original;

    @Column(name = "img_saved")
    private String saved;

    // 상품 id 컬럼 외래키로 가짐
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public ItemImage(String original, String saved) {
        this.original = original;
        this.saved = saved;
    }
}
