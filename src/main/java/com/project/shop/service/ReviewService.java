package com.project.shop.service;

import com.project.shop.dto.ItemDto;
import com.project.shop.dto.OrderDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    // 상품 id를 매개변수로 주문 정보 조회하는 서비스
    // 조회한 주문 정보에 회원 id 가 일치하는 기록이 있으면 리뷰 작성 페이지로
    // 아니라면 예외 발생
    // 리뷰 폼을 요청할 때 상품 정보, 주문 일자를 dto로 넘겨주고 넘겨받은
    // 데이터를 검증 절차를 통해 현재 로그인 된 유저가 리뷰를 쓸 수 있는지
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

        if(order == null) {
            return reviewItemDto;
        } else {
            reviewItemDto.setOrderDate(order.getOrderDate());
            reviewItemDto.setMemberId(member.getId());
            return reviewItemDto;
        }
    }

    public Long newReview(ReviewDto reviewDto, String email) {
        // 주문된 상품 정보를 해당 id 값을 이용하여 검색. 없을 경우 예외 출력
        Item item = itemRepository.findById(reviewDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        // 주문한 멤버 정보를 얻어오기. 어떻게?
        Member member = memberRepository.findByEmail(email);

        // 가져온 멤버와 주문 상품 내역을 주문 내역에 추가
        Review review = Review.createReview(member, item, reviewDto.getContent());
        reviewRepository.save(review);

        // 해당 주문의 id를 출력
        return review.getId();
    }
}
