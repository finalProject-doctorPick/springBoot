package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 글목록 검색
	@RequestMapping("/getBoardList")
	public String getBoardList(Model m) {
		m.addAttribute("boardList", boardService.getBoardList());
		return "getBoardList";
	}
}
