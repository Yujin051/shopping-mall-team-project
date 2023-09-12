package com.project.shop.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_title")
    private String title;

    @Column(name = "review_content")
    private String content;

    // 회원 테이블과 연관관계 설정. 리뷰 다수 <-> 회원 하나
    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Column(name = "review_date")
    @CreatedDate
    private LocalDateTime date;
}

