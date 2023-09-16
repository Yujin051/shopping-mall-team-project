package com.project.shop.repository;

import com.project.shop.dto.OrdersManageDto;

import com.project.shop.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select new com.project.shop.dto.OrdersManageDto(o.id, i.id, o.orderCount, o.orderPrice, i.itemName, o.orderDate, m.email, o.orderStatus, i.imgSaved) from Order o join o.item i join o.member m")
    List<OrdersManageDto> findOrdersManageDto();

    @Query("select new com.project.shop.dto.OrdersManageDto(i.id, o.orderStatus, o.orderDate, i.itemName, o.orderPrice, i.imgSaved) from Order o join o.item i join o.member m where m.id = :id")
    List<OrdersManageDto> findOrderMyManageDto(Long id);
}