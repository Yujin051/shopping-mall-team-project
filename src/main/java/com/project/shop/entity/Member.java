package com.project.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.shop.constant.RoleType;
import com.project.shop.dto.MemberFormDto;
import com.project.shop.service.MemberService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
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
	
	@Column(name = "regdate")
	@CreatedDate
	private LocalDate date;

	// 리뷰 테이블과 연관관계 설정. 회원 하나 <-> 리뷰 다수
	// cascade 설정 : 연관된 데이터가 삭제될 경우 자기 데이터도 삭제됨
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();

	@Builder
	public Member(String email, String password, String phonenum, String auth, RoleType role, LocalDate date) {
		this.email = email;
		this.password = password;
		this.phonenum = phonenum;
		this.role = role;
		this.date = date;
	}
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = Member.builder()
				.email(memberFormDto.getEmail())
				.password(passwordEncoder.encode(memberFormDto.getPassword()))
				.phonenum(memberFormDto.getPhonenum())
				.role(RoleType.ADMIN)
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
