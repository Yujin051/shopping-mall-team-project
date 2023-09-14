package com.project.shop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    // 장바구니 상품 정보 담는 DTO
    @NotNull(message = "상품 아이디는 필수값입니다.")
    private Long itemId;

    @Min(value = 1, message = "1개 이상부터 담을 수 있습니다.")
    private int count;


}
