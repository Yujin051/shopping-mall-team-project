package com.project.shop.service;

import com.project.shop.entity.Item;
import com.project.shop.entity.ItemImg;
import com.project.shop.repository.ItemImgRepository;
import com.project.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ItemImgService {


    private final ItemImgRepository itemImgRepository;

    private final ItemRepository itemRepository;

    @Value("${spring.servlet.multipart.location}")
    String imgPath;

    public String getFullPath(String filename) {
        return imgPath + filename;
    }
    public ItemImg saveImg(MultipartFile multipartFile, Item item) throws Exception{

        if(multipartFile.isEmpty()) {
            return null;
        }

        // 원본 파일명
        String originalFilename = multipartFile.getOriginalFilename();

        // 서버에 저장된 파일명
        // 파일명이 중복될 수 있으므로 UUID로 설정
        String savedFilename = UUID.randomUUID() + "." + extractExt(originalFilename);

        // 파일 저장
        multipartFile.transferTo(new File(getFullPath(savedFilename)));

        return itemImgRepository.save(ItemImg.builder()
                .imgOriginal(originalFilename)
                .imgSaved(savedFilename)
                .build());
    }

    // 이미지 파일의 확장자 뽑아내기
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
