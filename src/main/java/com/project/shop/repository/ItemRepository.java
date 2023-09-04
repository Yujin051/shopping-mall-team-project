package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
	

}
