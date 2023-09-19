package com.project.shop;

import com.project.shop.constant.RoleType;
import com.project.shop.entity.Member;
import com.project.shop.repository.MemberRepository;
import com.project.shop.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.project.shop.entity.Item;
import com.project.shop.repository.ItemImageRepository;
import com.project.shop.repository.ItemRepository;
import com.project.shop.repository.ReviewRepository;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
@Slf4j
public class ShopProjectApplication implements CommandLineRunner {

	private final ItemRepository itemRepository;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ShopProjectApplication.class, args);
	}

	// CommandLineRunner를 이용하기 위해 run 메소드 구현
	// 임시 데이터 INSERT 용으로 넣었습니다.
    // 추후 DB 이전 할 때 삭제할 것

	@Override
	public void run(String... args) {
		// ADMIN 계정 1개 INSERT
		// memberRepository.save(new Member("Admin@admin.com",passwordEncoder.encode("123123"),"01012345678","",RoleType.ADMIN, LocalDate.now()));


	}

}
