package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "item_img")
public class ItemImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    // 원본 이미지 이름
    @Column(name = "img_original")
    private String imgOriginal;

    // db에 저장될 이미지 이름
    @Column(name = "img_saved")
    private String imgSaved;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public ItemImg(String imgOriginal, String imgSaved, Item item) {
        this.imgOriginal = imgOriginal;
        this.imgSaved = imgSaved;
        this.item = item;
    }



}
