package com.project.shop.repository;

import com.project.shop.dto.CartDto;
import com.project.shop.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // 장바구니에 담긴 상품 찾는 JPA 쿼리 메소드
    CartItem findByCartIdAndItemId(Long CartId, Long ItemId);

    // 장바구니 id를 기준으로 cartiemlist 조회하기
    // JPQL 사용해서 DTO 반환, 반환된 DTO를 프론트로 전달
    @Query("select new com.project.shop.dto.CartDto(i.id, ci.id, i.itemName, i.itemPrice, ci.count, i.imgSaved) "
    + "from CartItem ci join ci.item i where ci.cart.id = :cartId " +
    "order by ci.id desc")
    List<CartDto> findCartDtoList(Long cartId);
}
