package com.project.shop.service;

import com.project.shop.dto.ItemDto;
import com.project.shop.entity.Item;
import com.project.shop.entity.ItemImg;
import com.project.shop.repository.ItemImgRepository;
import com.project.shop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {


    private final ItemRepository itemRepository;

    private final ItemImgRepository itemImgRepository;

    private final ItemImgService itemImgService;


    // 상품 등록
    @Transactional
    public Item writeItem(Item item) throws Exception {
        Item savedItem = itemRepository.save(item);

        ItemImg itemImg = itemImgService.saveImg((MultipartFile) item.getItemImg(), savedItem);

        if(itemImg != null) {
            savedItem.setItemImg(itemImg);
        }

        return itemImg.getItem();


        // itemRepository.save(item);
    }
}
