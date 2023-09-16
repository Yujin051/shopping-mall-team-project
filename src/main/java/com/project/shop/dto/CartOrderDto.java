package com.project.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartOrderDto {
    private Long cartItemId;
    private int price;
}
