package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReviewDto {
    // 리뷰 id
    private Long reviewId;

    // 아이템 id
    private Long itemId;

    // 멤버 id
    private Long memberId;

    // 리뷰 내용
    private String content;

    // 작성자 이메일
    private String email;

    // 작성 날짜
    private LocalDate date;
}
