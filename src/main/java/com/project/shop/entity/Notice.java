package com.project.shop.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "notice")
@EntityListeners(AuditingEntityListener.class)
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
	
	@Builder
	public Notice(Long id, String title, String content, LocalDate date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.date = date;
	}
}

