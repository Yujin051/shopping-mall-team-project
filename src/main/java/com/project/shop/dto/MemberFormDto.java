package com.project.shop.dto;



import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class MemberFormDto {
	
	@NotBlank(message = "이메일은 필수 입력 사항입니다")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String email;
	
	@NotBlank(message = "비밀번호는 필수 입력 사항입니다")
	@Length(min = 4, max = 16, message = "비밀번호는 4자 이상 16자 이하로 입력해주세요")
	private String password;
	
	private String phonenum;

	private LocalDate date;

	@Builder
	public MemberFormDto(String email, String password, String phonenum, LocalDate date) {
		this.email = email;
		this.password = password;
		this.phonenum = phonenum;
		this.date = date;
		
	}

	
}
