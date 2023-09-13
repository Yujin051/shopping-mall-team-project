package com.project.shop.repository.search;

import com.project.shop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemSearch {

    // 단어로 검색
    Page<Item> searchAll(String keyword, Pageable pageable);

    // 메인 카테고리로 검색
    Page<Item> searchByMainCate(String keyword, Pageable pageable);

    // 서브 카테고리로 검색
    Page<Item> searchBySubCate(String keyword, Pageable pageable);

}
