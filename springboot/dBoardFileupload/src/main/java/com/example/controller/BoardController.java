package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Board;
import com.example.service.BoardService;

@RestController
@RequestMapping("/")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "REST 컨트롤러";
	}
	
	@GetMapping("/board")
	public List<Board> getBoardList() {
		return boardService.getBoardList();
	}
	
	@GetMapping("/board/{seq}")
	public Board getBoard(@PathVariable Integer seq) {
		return boardService.getBoard(seq);
	}
	
	@PostMapping("/board/write")
	public void insertBoard(Board v) {
		boardService.saveBoard(v, null);
	}
}
