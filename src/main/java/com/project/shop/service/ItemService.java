package com.project.shop.service;

import com.project.shop.entity.Item;
import com.project.shop.repository.ItemImgRepository;
import com.project.shop.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private  ItemImgRepository itemImgRepository;

    // 상품 등록
    public void newItem(Item item) {
        itemRepository.save(item);
    }
}
