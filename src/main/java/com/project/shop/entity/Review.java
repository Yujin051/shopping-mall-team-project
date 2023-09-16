package com.project.shop.entity;

import groovy.transform.builder.Builder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
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
    @JoinColumn(name = "member_id")
    private Member member;

    // 상품 테이블과 연관관계 설정, 리뷰 다수 <-> 상품 하나
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "review_date")
    @CreatedDate
    private LocalDateTime date;

    // 리뷰 객체 생성하기
    public static Review createReview(Member member, Item item, String title, String content) {
        Review review = new Review();

        review.setMember(member);
        review.setItem(item);
        review.setTitle(title);
        review.setContent(content);

        return review;
    }
}

