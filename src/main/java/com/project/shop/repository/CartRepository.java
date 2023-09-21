package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

    // 회원 id 컬럼으로 해당 회원의 장바구니 검색
    Cart findByMemberId(Long memberId);
}
