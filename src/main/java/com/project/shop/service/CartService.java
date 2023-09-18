package com.project.shop.service;

import com.project.shop.dto.CartDto;
import com.project.shop.dto.CartItemDto;
import com.project.shop.dto.CartOrderDto;
import com.project.shop.dto.OrderDto;
import com.project.shop.entity.Cart;
import com.project.shop.entity.CartItem;
import com.project.shop.entity.Item;
import com.project.shop.entity.Member;
import com.project.shop.repository.CartItemRepository;
import com.project.shop.repository.CartRepository;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

    // 사용할 레포지토리 의존성 주입
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderService orderService;

    // 장바구니 추가하는 메소드
    public Long addCart(CartItemDto cartItemDto, String email) {

        // 해당 회원 검색 후 장바구니 정보 얻어오기
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        // 장바구니가 없다면 새로 생성
        if(cart == null) {
            cart = Cart.createCart(member);
            cartRepository.save(cart);
        }

        // 장바구니에 담길 상품 cartItemDto의 값으로 찾아오기
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        // 찾아온 상품 id로 장바구니 상품 엔티티에 있는지 확인하기
        CartItem cartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        // 찾아온 상품 엔티티가 장바구니 상품 엔티티에 없다면 새로 추가
        if(cartItem == null) {
            cartItem = CartItem.builder()
                    .cart(cart)
                    .item(item)
                    .count(cartItemDto.getCount())
                    .build();
            cartItemRepository.save(cartItem);
        } else {
            // 해당 상품이 이미 존재한다면 수량만 추가로 증가
            cartItem.addCount(cartItemDto.getCount());
        }
        return cartItem.getId();
    }

    // 장바구니 찾는 메소드
    public List<CartDto> findCart(String email) {
        // 장바구니 값 전달용 Dto 선언
        List<CartDto> cartDtoList = new ArrayList<>();

        // email로 회원 탐색 후 해당 회원의 장바구니 가져오기
        Member member = memberRepository.findByEmail(email);
        Cart cart = cartRepository.findByMemberId(member.getId());

        // 장바구니가 없다면 빈 Dto 반환
        if(cart == null) {
            return cartDtoList;
        }

        // 해당 장바구니 id로 장바구니 상품 조회 후 Dto로 반환
        cartDtoList = cartItemRepository.findCartDtoList(cart.getId());
        return cartDtoList;
    }

    // 로그인한 회원과 장바구니 소유 회원이 같은지 검증하는 서비스
    @Transactional
    public boolean validateCartItem(Long cartItemId, String email){
        // 현재 로그인한 유저
        Member currentMember = memberRepository.findByEmail(email);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(EntityNotFoundException::new);
        Member cartMember = cartItem.getCart().getMember();

        return StringUtils.equals(currentMember.getEmail(), cartMember.getEmail());
    }

    // 장바구니 상품 삭제하는 메소드
    public void deleteCartItem(Long cartItemId) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        cartItemRepository.delete(cartItem);
        cartItemRepository.deleteById(cartItemId);
    }

    // 장바구니 상품 주문 - 주문된 상품 삭제하는 메소드
    public Long orderCartItem(CartOrderDto cartOrderDto, String email) {
        OrderDto orderDto = new OrderDto();
        CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
                .orElseThrow(EntityNotFoundException::new);

        orderDto.setItemId(cartItem.getItem().getId());
        orderDto.setPrice(cartOrderDto.getPrice());
        orderDto.setCount(cartItem.getCount());

        Long orderId = orderService.cartOrder(orderDto, email);
        cartItemRepository.delete(cartItem);

        return orderId;
    }
}
