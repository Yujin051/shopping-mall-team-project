package com.project.shop.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDto {
    // Validate 활용한 각 값의 최소 입력값 결정
    @NotNull(message = "상품 ID는 필수 입력값입니다.")
    private Long itemId;

    // 상품은 1~99개 사이에서 주문할 수 있도록
    @Min(value = 1, message = "최소 주문 수량은 1개입니다.")
    @Max(value = 99, message = "최대 주문 수량은 99개입니다.")
    private int count;
}
