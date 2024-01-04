package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.BoardVO;

// @Controller > 뷰 페이지 필요 (html, jsp)
@RestController
public class SampleController {

	@RequestMapping("/hello")
	public String Hello() {
		return "안녕 부트";
	}
	
	@RequestMapping("/hello2")
	public BoardVO hello2() {
		BoardVO vo = new BoardVO();
		vo.setCnt(1000);
		vo.setTitle("제목");
		vo.setWriter("아버지");
		return vo;
	}
	
	@RequestMapping("/hello3")
	public List<BoardVO> hello3(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		for(int i = 0; i < 3; i++) {
			BoardVO vo = new BoardVO();
			vo.setSeq(i);
			vo.setTitle("제목" + i);
			vo.setContent("내용" + i);
			list.add(vo);
		}
		
		return list;
	}
}
