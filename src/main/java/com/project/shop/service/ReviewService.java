package com.project.shop.service;

import com.project.shop.dto.ReviewDto;
import com.project.shop.dto.ReviewItemDto;
import com.project.shop.entity.Item;
import com.project.shop.entity.Member;
import com.project.shop.entity.Review;
import com.project.shop.orders.Order;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.MemberRepository;
import com.project.shop.repository.OrderRepository;
import com.project.shop.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    // 상품 id를 매개변수로 주문 정보 조회하는 서비스
    // 조회한 주문 정보에 회원 id 가 일치하는 기록이 있으면 리뷰 작성 페이지로
    // 아니라면 예외 발생 --> 발생한 예외 처리 후 원래 상품 페이지로 이동하고 싶은데
    // 어떤 식으로?
    // 리뷰 폼을 요청할 때 상품 정보, 주문 일자를
    // 확인한 후에 리뷰 작성 페이지를 열고, 작성된 리뷰를 해당 상품 상세 페이지
    // 로 넘겨주면 그 값을 받아서 출력하는

    // 주문 일자 반환하기 위한 삽질 - 그런데 여기서 검증까지 하려니까 실패했음
    public ReviewItemDto reviewItem(Long itemId, String email) {
        // 넘겨받은 상품 id로 리뷰 쓸 상품 정보 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        // 회원 정보 조회
        Member member = memberRepository.findByEmail(email);

        // 가져온 상품id와 회원id로 주문 정보 조회
        Order order = orderRepository.findByItemIdAndMemberId(item.getId(), member.getId());

        // 정보를 반환할 Dto 선언
        ReviewItemDto reviewItemDto = new ReviewItemDto();

        if (order != null) {
            // 가져온 정보를 Dto로 전달
            reviewItemDto.setOrderDate(order.getOrderDate());
            reviewItemDto.setMemberId(member.getId());
        }
        return reviewItemDto;
    }

    public Long newReview(ReviewDto reviewDto, String email) {
        // 리뷰 정보 상품 id를 통해서 찾기
        Item item = itemRepository.findById(reviewDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        // 주문한 멤버 정보를 얻어오기
        Member member = memberRepository.findByEmail(email);

        // 가져온 멤버와 상품을 기준으로 새로운 리뷰 추가
        Review review = Review.createReview(member, item, reviewDto.getContent());
        reviewRepository.save(review);

        // 해당 리뷰의 id 출력
        return review.getId();
    }

    // 후기id로 후기 삭제하는 메소드
    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(EntityNotFoundException::new);
        reviewRepository.delete(review);
    }
}
