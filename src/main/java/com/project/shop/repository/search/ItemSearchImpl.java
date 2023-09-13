package com.project.shop.repository.search;

import com.project.shop.entity.Item;
import com.project.shop.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ItemSearchImpl extends QuerydslRepositorySupport implements ItemSearch{

    public ItemSearchImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchAll(String keyword, Pageable pageable) {

        QItem item = QItem.item;
        JPQLQuery<Item> query = from(item);

        // 키워드가 있다면 키워드로 검색
        if(keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();

            booleanBuilder.or(item.itemName.contains(keyword));

            query.where(booleanBuilder);
        }

        // 상품번호가 0 이상일 때만
        query.where(item.id.gt(0L));

        // 페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Item> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Item> searchByMainCate(String keyword, Pageable pageable) {

        QItem item = QItem.item;
        JPQLQuery<Item> query = from(item);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 메인 카테고리는 정해진 하나의 값만 들어가므로
        // 일치하는 문자열을 찾기
        booleanBuilder.or(item.mainCate.eq(keyword));

        query.where(booleanBuilder);

        // 상품번호가 0 이상일 때만
        query.where(item.id.gt(0L));

        // 페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Item> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public Page<Item> searchBySubCate(String keyword, Pageable pageable) {

        QItem item = QItem.item;
        JPQLQuery<Item> query = from(item);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 서브 카테고리는 여러 문자열이 쌓여있는 형태이므로
        // 포함된 문자열 찾기 - Like 구문
        booleanBuilder.or(item.subCate.contains(keyword));

        query.where(booleanBuilder);

        // 상품번호가 0 이상일 때만
        query.where(item.id.gt(0L));

        // 페이징
        this.getQuerydsl().applyPagination(pageable, query);

        List<Item> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
