package com.project.shop.repository;

import com.project.shop.repository.search.ItemSearch;
import com.project.shop.service.ItemService;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemSearch {

}
