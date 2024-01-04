package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.persistence.SampleRepository;

@Controller
public class SampleController {

	// 원래는 서비스단을 주입하지만 레포지토리 바로 주입
	@Autowired
	private SampleRepository sampleRepository;
	
	@RequestMapping("/sample")
	public String sample(Model m) {
		// 제목이 test인 레코드 검색
		// m.addAttribute("boardList", sampleRepository.findByTitle("제목"));
		
		// 작성자가 홍길동인 레코드 검색
		// m.addAttribute("boardList", sampleRepository.findByWriter("홍길동"));
		
		// cnt가 null인 레코드 검색
//		m.addAttribute("boardList", sampleRepository.findByCntIsNull());
		
		// cnt가 0인 레코드
//		m.addAttribute("boardList", sampleRepository.findByCnt(0));
		
		// 제목이 test 이거나 작성자가 홍길동인 레코드 검색
		m.addAttribute("boardList", sampleRepository.findByTitleOrWriter("test", "홍길동"));
		
		// 작성자 중 "홍"이 포함되어 있느 레코드 검색
//		m.addAttribute("boardList", sampleRepository.findByContainingWriter("홍"));
		
		return "/board/getBoardList";
	}
}
