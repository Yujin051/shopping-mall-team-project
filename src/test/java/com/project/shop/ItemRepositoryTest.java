package com.project.shop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.shop.entity.Item;
import com.project.shop.repository.ItemRepository;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .itemName("테스트 상품")
                .itemPrice(10000)
                .itemContent("테스트 상품 상세설명")
                .itemQty(100)
                .build();
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
}
