package com.project.shop.service;

import com.project.shop.dto.ItemDto;
import com.project.shop.dto.PageRequestDto;
import com.project.shop.dto.PageResponseDto;
import com.project.shop.entity.Item;

import java.util.List;

public interface ItemService {

    // 아이템 테이블에서 전체 데이터 불러오는 것으로 처리
    public List<Item> itemList();

    // 아이템 상세페이지
    public Item itemView(Long id);

    // 아이템 정렬 메소드
    PageResponseDto<ItemDto> list(PageRequestDto pageRequestDto);

    // 아이템 메인카테고리로 찾기

    // 아이템 서브카테고리로 찾기
    PageResponseDto<ItemDto> subList(PageRequestDto pageRequestDto, String keyword);
}
