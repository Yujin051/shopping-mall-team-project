package com.project.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	// 유저 정보 DB와 통신하는 DTO
	
	private String name;
	private String email;
	private String password;
	private String phoneNumber;
}
