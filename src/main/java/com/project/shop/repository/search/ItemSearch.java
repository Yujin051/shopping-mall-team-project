package com.project.shop.repository.search;

import com.project.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemSearch {

    // 단어로 검색하기
    Page<Item> searchAll(String keyword, Pageable pageable);
}
