package com.project.shop;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.project.shop.entity.OrderItem;
import com.project.shop.entity.Orders;
import com.project.shop.repository.OrderItemRepository;
import com.project.shop.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class ShopProjectApplication implements CommandLineRunner {

	private final OrdersRepository ordersRepository;
	private final OrderItemRepository orderItemRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ShopProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// 주문정보 테이블
		ordersRepository.save(new Orders("s@naver.com", 1234, "판매자 확인중", LocalDate.now(), 30000, 3));
		ordersRepository.save(new Orders("a@naver.com", 1235, "판매자 확인중", LocalDate.now(), 20000, 2));
		ordersRepository.save(new Orders("non@naver.com", 1236, "판매자 확인중", LocalDate.now(), 10000, 4));
		ordersRepository.save(new Orders("sdas@naver.com", 1237, "판매자 확인중", LocalDate.now(), 91000, 1));
		ordersRepository.save(new Orders("hi@naver.com", 1238, "판매자 확인중", LocalDate.now(), 55000, 2));
		
			
	}

}
