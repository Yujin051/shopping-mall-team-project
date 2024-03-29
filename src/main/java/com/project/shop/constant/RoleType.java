package com.project.shop.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
	USER("ROLE_USER", "고객"),
	ADMIN("ROLE_ADMIN", "관리자");
	
	private final String key;
	private final String title;
}
