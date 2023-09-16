package com.project.shop.service;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.shop.dto.ItemDto;
import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.entity.Item;
import com.project.shop.entity.Review;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ItemService {

    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;
    private final ReviewRepository reviewRepository;

    @Value("${spring.servlet.multipart.location}")
    String imgPath;

    public String getFullPath(String filename) {
        return imgPath + filename;
    }

    // 이미지 파일의 확장자 뽑아내기

    // 상품 등록
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
    
    // 상품 정렬 페이지 관련, 페이징 관련
    // 인터페이스로 분리하여 상속받은 내용 다시 서비스로 통합(ItemServiceImpl)
    // 아이템 테이블에서 전체 데이터 불러오는 것으로 처리

    // 아이템 리뷰 리스트 불러오기
    List<Review> reviewList(Long itemId){
        return reviewRepository.findAllByItemId(itemId);
    };

       // 아이템 정렬 메소드. 전체 상품 정렬이라 안 씀
    public PageResponseDto<ItemDto> list(PageRequestDto pageRequestDto){

        String keyword = pageRequestDto.getKeyword(); // 검색할 단어 얻어오기
        Pageable pageable = pageRequestDto.getPageable("id"); // 페이징

        Page<Item> result = itemRepository.searchAll(keyword, pageable);

        // 찾아온 페이지를 item의 DTO로 변환
        List<ItemDto> dtoList = result.getContent().stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        // 응답 객체에 요청한 정보 DTO로 변환하여 전달
        return PageResponseDto.<ItemDto>withAll()
                .pageRequestDto(pageRequestDto)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    };

    // 아이템 메인카테고리로 찾기
    public PageResponseDto<ItemDto> mainList(PageRequestDto pageRequestDto, String keyword){
        Pageable pageable = pageRequestDto.getPageable("id"); // 페이징

        Page<Item> result = itemRepository.searchByMainCate(keyword, pageable);

        // 찾아온 페이지를 item의 DTO로 변환
        List<ItemDto> dtoList = result.getContent().stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        // 응답 객체에 요청한 정보 DTO로 변환하여 전달
        return PageResponseDto.<ItemDto>withAll()
                .pageRequestDto(pageRequestDto)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    };

    // 아이템 서브카테고리로 찾기
    public PageResponseDto<ItemDto> subList(PageRequestDto pageRequestDto, String keyword){
        Pageable pageable = pageRequestDto.getPageable("id"); // 페이징

        Page<Item> result = itemRepository.searchBySubCate(keyword, pageable);

        // 찾아온 페이지를 item의 DTO로 변환
        List<ItemDto> dtoList = result.getContent().stream()
                .map(item -> modelMapper.map(item, ItemDto.class))
                .collect(Collectors.toList());

        // 응답 객체에 요청한 정보 DTO로 변환하여 전달
        return PageResponseDto.<ItemDto>withAll()
                .pageRequestDto(pageRequestDto)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    };
}
  
   

