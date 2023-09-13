package com.project.shop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDto<E> {

    // 화면의 DTO와 시작 페이지/끝 페이지 대한 처리를 담당
    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;

    // 끝 페이지 번호
    private int end;

    // 이전 페이지 존재 여부
    private boolean prev;

    // 다음 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;

    // 페이징 기능 처리 메소드
    @Builder(builderMethodName = "withAll")
    public PageResponseDto(PageRequestDto pageRequestDto, List<E> dtoList, int total) {

        if(total <= 0) {
            return;
        }

        this.page = pageRequestDto.getPage();
        this.size = pageRequestDto.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 20.0) * 20); // 화면에서의 마지막 번호

        this.start = this.end - 19; // 화면에서의 시작 번호

        int last = (int)(Math.ceil(total/(double)size)); // 데이터의 개수에 따른 마지막 페이지 번호

        this.end = Math.min(end, last); // 마지막 페이지 설정 함수

        // 아마 앞뒤 페이지 이동 기능인 것 같은데 일단 보류
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;
    }

}
