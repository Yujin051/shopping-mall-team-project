package com.project.shop.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notice")
public class Notice {

	@Id
	@Column(name = "notice_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "notice_title")
	private String title;
	
	@Column(name = "notice_cotent")
	private String content;
	
	@Column(name = "notice_date")
	@CreatedDate
	private LocalDate date;
}

