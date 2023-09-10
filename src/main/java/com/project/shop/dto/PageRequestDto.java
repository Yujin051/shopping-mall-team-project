package com.project.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDto {
    // 페이징 처리를 위한 Dto

    // 기본 페이지 번호
    @Builder.Default
    private int page =1;

    // 최대 페이지 종류
    @Builder.Default
    private int size = 20;

    private String type; // 검색 종류 설정하는 기능, 추후 구현

    private String keyword;

    private String link;

    // 페이징 처리를 위해 Pageable 타입 반환 기능
    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    // 검색 조건을 문자열로 바꾸는 getLink() 메소드
    public String getLink() {
        if(link == null) {
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);

            if(keyword != null) {
                try {
                    builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
                } catch (UnsupportedEncodingException e) {

                }
            }
            link = builder.toString();
        }
        return link;
    }
}
