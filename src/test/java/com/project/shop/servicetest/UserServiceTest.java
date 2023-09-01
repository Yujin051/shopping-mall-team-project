package com.project.shop.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.shop.dto.UserDTO;
import com.project.shop.entity.User;
import com.project.shop.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	// Controller 에서 POST 값을 기반으로 userDTO 생성 예정
	public User createUser() {
		UserDTO userDTO = new UserDTO();
		userDTO.setEmail("test@test.com");
		userDTO.setName("홍길동");
		userDTO.setPassword("test1234");
		userDTO.setPhoneNumber("010-1234-1234");
		
		return User.createUser(userDTO);
	}
	
	
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveUserTest() {
		User user = createUser();
		User savedUser = userService.saveUser(user);
		
		// 단순 user - savedUser 값 비교
		assertEquals(user.getEmail(), savedUser.getEmail());
		assertEquals(user.getName(), savedUser.getName());
		assertEquals(user.getPassword(), savedUser.getPassword());
		assertEquals(user.getPhoneNumber(), savedUser.getPhoneNumber());
	}
	
	@Test
	@DisplayName("중복 회원가입 테스트")
	void validateDuplicateUser() {
		//given
		User user1 = createUser();
		User user2 = createUser();
		userService.saveUser(user1);
		
		// when
		Throwable e = assertThrows(IllegalStateException.class, () -> {
			userService.saveUser(user2);
		});
		
		// then
		assertEquals("중복된 이메일입니다.", e.getMessage());
	}
}
