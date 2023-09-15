package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewItemDto {
    // 주문한 회원 id
    private Long memberId;

    // 리뷰 대상 상품 주문일자
    private LocalDateTime orderDate;
}
