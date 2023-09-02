package com.project.shop.entity;

import com.project.shop.constant.Role;
import com.project.shop.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_list")
@Getter
@Setter
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// H2 DB 임시로 사용할 것이므로 id(pk값) 자동생성
	private Long id;
	
	private String name;
	
	// 이메일은 중복될 수 없으므로 @unique 설정
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	// 유저 역할 구분(관리자, 일반회원)을 위한 상수 타입 추가
	// 명시적으로 구분하기 위해 String 타입 사용
	@Enumerated(EnumType.STRING)
	private Role role;
	
	// 정적 팩토리 메소드? 생성자 대신 사용
	public static User createUser(UserDTO userDTO) {
		
		User user = new User();
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		
		// 임시로 시큐리티를 적용하지 않고 DB에 직접 암호 rawdata 저장하는 방식
		user.setPassword(userDTO.getPassword());
		
		return user;
	}
	
}
