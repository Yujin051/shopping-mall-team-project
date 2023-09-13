package com.project.shop.service;

import com.project.shop.entity.Item;
import com.project.shop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemService {


    private final ItemRepository itemRepository;

    @Value("${spring.servlet.multipart.location}")
    String imgPath;

    public String getFullPath(String filename) {
        return imgPath + filename;
    }

    // 이미지 파일의 확장자 뽑아내기

    // 상품 등록
    @Transactional
    public void itemWrite(Item item, MultipartFile file) throws Exception{

        // 원본 파일명
        String originalFilename = file.getOriginalFilename();

        // 서버에 저장된 파일명
        // 파일명이 중복될 수 있으므로 UUID로 설정
        String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        file.transferTo(new File(getFullPath(savedFilename)));

        // 오리지널 이미지 이름 저장
        item.setImgOriginal(originalFilename);

        // DB 이미지 이름 저장
        item.setImgSaved(savedFilename);

        itemRepository.save(item);
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

    // 상품 리스트 보이기
    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    public Item itemView(Long id) {
        return itemRepository.findById(id).get();
    }

    public void itemDelete(Long id) {
        itemRepository.deleteById(id);

    }

}
