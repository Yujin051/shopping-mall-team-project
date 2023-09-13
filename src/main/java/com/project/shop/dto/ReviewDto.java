package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    // 작성자 이메일
    private String memberEmail;

    // 리뷰 내용
    private String content;

    // 리뷰 작성일
    private LocalDateTime date;

}
