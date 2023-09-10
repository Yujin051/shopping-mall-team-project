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
        Pageable pageable = pageRequestDto.getPageable("ino"); // 페이징

        Page<Item> result = itemRepository.searchAll(keyword, pageable);

        return null;
    }
}
