package com.project.shop.service;

import com.project.shop.dto.OrderDto;
import com.project.shop.entity.Item;
import com.project.shop.entity.Member;
import com.project.shop.orders.Order;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.MemberRepository;
import com.project.shop.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
// 무결성 위한 트랜잭션 처리
@Transactional
@RequiredArgsConstructor
public class OrderService {
    // 주문 서비스를 위한 레포지토리 호출
    // 의존성 주입은 @RequiredArgsConstructor 통해서
    private final MemberRepository memberRepository; // 회원 정보 불러오기
    private final OrderRepository orderRepository;   // 주문 정보 불러오기
    private final ItemRepository itemRepository;     // 상품 정보 불러오기

    // 주문 객체 생성하기
    public Long order(OrderDto orderDto, String email) {
        // 주문된 상품 정보를 해당 id 값을 이용하여 검색. 없을 경우 예외 출력
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        // 주문한 멤버 정보를 얻어오기. 어떻게?
        Member member = memberRepository.findByEmail(email);

        // 가져온 멤버와 주문 상품 내역을 주문 내역에 추가
        Order order = Order.createOrder(member, item);
        orderRepository.save(order);

        // 해당 주문의 id를 출력
        return order.getId();
    }

}
