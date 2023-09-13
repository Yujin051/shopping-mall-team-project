package com.project.shop.service;

import com.project.shop.dto.ItemDto;
import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.entity.Item;
import com.project.shop.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceImpl implements ItemService {
    private final ModelMapper modelMapper;

    private final ItemRepository itemRepository;

    public List<Item> itemList() {
        return itemRepository.findAll();
    }

    // 상품 상세페이지
    public Item itemView(Long id) {
        return itemRepository.findById(id).get();
    }

    // 상품 정렬
    @Override
    public PageResponseDto<ItemDto> list(PageRequestDto pageRequestDto) {

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
    }

    // 메인 카테고리로 검색하여 정렬
    @Override
    public PageResponseDto<ItemDto> mainList(PageRequestDto pageRequestDto, String keyword) {
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
    }


    // 서브카테고리로 검색하여 정렬
    @Override
    public PageResponseDto<ItemDto> subList(PageRequestDto pageRequestDto, String keyword) {
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
    }

}
