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
}
