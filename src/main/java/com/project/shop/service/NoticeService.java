package com.project.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shop.entity.Notice;
import com.project.shop.repository.NoticeRepository;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	// 공지 작성
	public void write(Notice notice) {
		noticeRepository.save(notice);
	}
	
	// 공지 전체 리스트 보기
	public List<Notice> noticeList() {
		return noticeRepository.findAll();
	}
	
	// 공지 상세보기
	public Notice noticeView(Long id) {
		return noticeRepository.findById(id).get();
	}
	
	public void noticeDelete(Long id) {
		noticeRepository.deleteById(id);
	}
	
}
