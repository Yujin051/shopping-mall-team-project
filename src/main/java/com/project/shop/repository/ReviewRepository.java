package com.project.shop.repository;

import com.project.shop.dto.ReviewDto;
import com.project.shop.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByItemId(Long itemId);

    Review findByItemId(Long itemId);

    // 상품 id 로 해당 상품의 리뷰 리스트 불러오는 쿼리
    @Query("select new com.project.shop.dto.ReviewDto(r.id, i.id, m.id, r.content, m.email, r.date)"
    + "from Review r join r.item i join r.member m where r.item.id = :id")
    List<ReviewDto> findReviewDtoList(Long id);
}
