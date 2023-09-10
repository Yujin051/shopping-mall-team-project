package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Item itmem;

    @Builder
    public ItemImg(String imgOriginal, String imgSaved) {
        this.imgOriginal = imgOriginal;
        this.imgSaved = imgSaved;
    }



}
