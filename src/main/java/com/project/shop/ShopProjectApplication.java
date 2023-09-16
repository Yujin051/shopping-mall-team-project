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
import com.project.shop.entity.ItemImage;
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
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final ItemImageRepository itemImageRepository;
	private final ReviewRepository reviewRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ShopProjectApplication.class, args);
	}

	// CommandLineRunner를 이용하기 위해 run 메소드 구현
	// 임시 데이터 INSERT 용으로 넣었습니다.
    // 추후 DB 이전 할 때 삭제할 것
	@Override
	public void run(String... args) throws Exception {
		// ADMIN 계정 1개 INSERT
		memberRepository.save(new Member("Admin@admin.com",passwordEncoder.encode("123123"),"01012345678","",RoleType.ADMIN, LocalDate.now()));
		// item 테이블에 임시 데이터 INSERT
		itemRepository.save(new Item("게임1", 50000, 99, "상세설명1", "게임", "액션어드벤처RPG" ));
		itemRepository.save(new Item("게임2", 50000, 99, "상세설명2", "게임", "액션슈팅" ));
		itemRepository.save(new Item("게임3", 50000, 10, "상세설명3", "게임", "스포츠" ));
		itemRepository.save(new Item("게임4", 50000, 99, "상세설명4", "게임", "파티아케이드" ));
		itemRepository.save(new Item("게임5", 50000, 99, "상세설명5", "게임", "전략" ));
		itemRepository.save(new Item("게임6", 50000, 99, "상세설명6", "게임", "음악" ));
		itemRepository.save(new Item("게임7", 50000, 99, "상세설명7", "게임", "격투트레이닝" ));
		itemRepository.save(new Item("게임8", 50000, 99, "상세설명8", "게임", "슈팅아케이드" ));
		itemRepository.save(new Item("게임9", 50000, 99, "상세설명9", "게임", "액션어드벤처" ));
		itemRepository.save(new Item("게임10", 50000, 99, "상세설명10", "게임", "RPG전략" ));
		itemRepository.save(new Item("게임11", 50000, 99, "상세설명11", "게임", "RPG전략" ));
		itemRepository.save(new Item("게임12", 50000, 99, "상세설명12", "게임", "액션어드벤처" ));
		itemRepository.save(new Item("게임13", 50000, 99, "상세설명13", "게임", "슈팅PRG" ));
		itemRepository.save(new Item("게임14", 50000, 99, "상세설명14", "게임", "슈팅RPG" ));
		itemRepository.save(new Item("게임15", 50000, 99, "상세설명15", "게임", "어드벤처RPG" ));
	}

}
