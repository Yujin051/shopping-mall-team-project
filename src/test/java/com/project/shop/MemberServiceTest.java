package com.project.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.project.shop.dto.MemberFormDto;
import com.project.shop.entity.Member;
import com.project.shop.service.MemberService;

import jakarta.transaction.Transactional;
@SpringBootTest
@Transactional
@TestPropertySource(properties = {"spring.config.location=classpath:application.yml"})
class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public Member createMember() {
		MemberFormDto memberFormDto = MemberFormDto.builder()
				.email("test@gmail.com")
				.password("1234")
				.phonenum("010-1234-5678")
				.build();
		return Member.createMember(memberFormDto, passwordEncoder);
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveUserTest() {
		Member member = createMember();
		Member savedUser = memberService.saveMember(member);
		
		assertEquals(member.getEmail(), savedUser.getEmail());
	}


}
