//package com.project.shop.service;
//
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import com.project.shop.domain.User;
//import com.project.shop.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//@Service
//public class UserDetailService implements UserDetailsService{
//	private final UserRepository userRepository;
//	
//	@Override
//	public User loadUserByUsername(String email) {
//		return userRepository.findByEmail(email)
//				.orElseThrow(() -> new IllegalArgumentException(email));
//	}
//}
