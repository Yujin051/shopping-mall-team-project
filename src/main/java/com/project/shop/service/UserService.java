package com.project.shop.service;

import org.springframework.stereotype.Service;

import com.project.shop.entity.User;
import com.project.shop.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public User saveUser(User user ) {
		validateDuplicateUser(user);
		return userRepository.save(user);
	}
	
	public void validateDuplicateUser(User user) {
		User findUser = userRepository.findByEmail(user.getEmail());
		if(findUser != null) {
			throw new IllegalStateException("중복된 이메일입니다.");
		}
	}
}
