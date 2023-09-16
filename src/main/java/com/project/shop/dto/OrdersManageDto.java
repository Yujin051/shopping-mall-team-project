package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class OrdersManageDto {
    private Long orderId;
    private Long itemId;
    private int orderCount;
    private int orderPrice;
    private String itemName;
    private Date orderDate;
    private String email;
    private String orderStatus;
    private String imgSaved;



    public OrdersManageDto(Long orderId, Long itemId, int orderCount, int orderPrice, String itemName, String orderStatus, String imgSaved) {
        this.orderId= orderId;
        this.itemId = itemId;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.itemName = itemName;
        this.orderStatus = orderStatus;
        this.imgSaved = imgSaved;
    }

    public OrdersManageDto(Long itemId, String orderStatus, Date orderDate, String itemName, int orderPrice, String imgSaved) {
        this.itemId = itemId;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.imgSaved = imgSaved;
    }
}
