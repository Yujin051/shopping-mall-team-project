package com.project.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findByEmail(String email);
}
