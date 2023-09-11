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
import java.nio.charset.StandardCharsets;

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
    private int size = 12;

    private String type; // 검색 종류 설정하는 기능.
                         // 제목 n, 메인 카테고리 m, 서브 카테고리 s 로 구별

    private String keyword;

    private String link;

    // 검색 조건을 Stirng[] 배열로 처리할 것이므로 type 문자열을 배열로 반환하는 기능
    public String[] getTypes() {
        if(type == null || type.isEmpty()) {
            return null; // 조건이 없는 경우 null값 반환
        }
        return type.split("");
    }

    // 페이징 처리를 위해 Pageable 타입 반환 기능
    public Pageable getPageable(String...props) {
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
    }

    // 검색 조건을 문자열로 바꾸는 getLink() 메소드
    public String getLink() {
        if(link == null) {
            StringBuilder builder = new StringBuilder();
            // intellij 기능 통해 builder.append 최적화
            // 아래도 동일
            builder.append("page=").append(this.page);
            builder.append("&size=").append(this.size);

            if(type != null) {
                builder.append("&keyword=")
                        .append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
            }

            if(keyword != null) {
                builder.append("&keyword=")
                        .append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
            }
            link = builder.toString();
        }
        return link;
    }
}
