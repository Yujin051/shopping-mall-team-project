package com.project.shop.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.shop.constant.RoleType;
import com.project.shop.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter
@Setter
@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	// 이름 뺌
//	@Column(name = "name")
//	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "phonenum")
	private String phonenum;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private RoleType role;

	@Builder
	public Member(String email, String password, String phonenum, String auth, RoleType role) {
		this.email = email;
		this.password = password;
		this.phonenum = phonenum;
		this.role = role;
	}
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = Member.builder()
				.email(memberFormDto.getEmail())
				.password(passwordEncoder.encode(memberFormDto.getPassword()))
				.phonenum(memberFormDto.getPhonenum())
				.role(RoleType.USER)
				.build();
		return member;
	}
	
	
	//지금 안쓰는것들
//	public User update(String name, String phonenum) {
//		this.name = name;
//		this.phonenum = phonenum;
//		
//		return this;
//	}
//	
//	public String getRoleKey() {
//		return this.role.getKey();
//	}

//	@Override // 권한 반환
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority("user"));
//	}
//
//	@Override
//	public String getUsername() {
//		return email;
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}

}
