package com.project.shop;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.shop.entity.Item;
import com.project.shop.entity.Member;
import com.project.shop.orders.Order;
import com.project.shop.orders.OrderItem;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.MemberRepository;
import com.project.shop.repository.OrderItemRepository;
import com.project.shop.repository.OrderRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        Item item = Item.builder()
                .itemName("테스트 상품")
                .itemPrice(10000)
                .itemContent("테스트 상품 상세설명")
                .itemQty(100)
                .build();

        return item;
    }

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest() {
        Order order = new Order();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        });

        orderRepository.saveAndFlush(order);
        em.clear();

        Order savedOrder = orderRepository.findById(order.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(3, savedOrder.getOrderItems().size());
    }

		public Order createOrder() {
        Order order = new Order();

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Item item = this.createItem();
            itemRepository.save(item);

            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .count(10)
                    .orderPrice(1000)
                    .order(order)
                    .build();

            order.getOrderItems().add(orderItem);
        });

        Member member = new Member();
        memberRepository.save(member);

        order.builder() // 이미 생성한 Order 객체의 빌더를 사용하여 수정??
                .member(member)
                .build();
        orderRepository.save(order);

        return order;
    }

		@Test
    @DisplayName("고아 객체 제거 테스트")
    public void orphanRemovalTest() {
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
        em.flush();
    }
		
		@Autowired
	    OrderItemRepository orderItemRepository;
	    
		@Test
		@DisplayName("지연 로딩 테스트")
		public void lazyLoadingTest() {
		    Order order = this.createOrder();
		    Long orderItemId = order.getOrderItems().get(0).getId();
		    em.flush();;
		    em.clear();
		    
		    OrderItem orderItem = orderItemRepository.findById(orderItemId)
		            .orElseThrow(EntityNotFoundException::new);
		    System.out.println("Order class : " + orderItem.getOrder().getClass());
		    System.out.println("========================================");
		    orderItem.getOrder().getOrderDate();
		    System.out.println("========================================");
		}
}

