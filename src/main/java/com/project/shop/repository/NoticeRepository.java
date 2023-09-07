package com.project.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shop.entity.Notice;


public interface NoticeRepository extends JpaRepository<Notice, Long>{

	//Notice FindById(Long id);
}
