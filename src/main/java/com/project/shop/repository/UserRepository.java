package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
