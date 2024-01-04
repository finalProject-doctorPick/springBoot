package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Board;
import com.example.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/{step}")
	public String viewPage(@PathVariable String step) {
		System.out.println("{step} 진입");
		return "board/" + step;
	}
	
	// 글목록 검색
	@RequestMapping("/getBoardList")
	public String getBoardList(Model m) {
		m.addAttribute("boardList", boardService.getBoardList());
		return "board/getBoardList";
	}
	
	@RequestMapping("/getBoard")
	public String getBoard(Board vo, Model m) {
	    Board result = boardService.getBoard(vo.getSeq());
	    m.addAttribute("board", result);
	    return "board/getBoard";
	}
	
	@RequestMapping("/saveBoard")
	public String savaBoard(Board vo) {
		boardService.saveBoard(vo);
		return "redirect:getBoardList";
	}
}
