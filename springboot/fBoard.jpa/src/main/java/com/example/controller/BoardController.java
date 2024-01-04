package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.BoardVO;
import com.example.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
    @GetMapping("/{step}")
    public String viewPage(@PathVariable String step) {
        return "/board/" + step;
    }
    
	// 글목록 검색
	@RequestMapping("/getBoardList")
	public String getBoardList(Model m) {
		m.addAttribute("boardList", boardService.getBoardList(new BoardVO()));
		return "/board/getBoardList";
	}
	
	// 글 저장하기
	@RequestMapping("/saveBoard")
	public String saveBoard(BoardVO vo) {
		boardService.saveBoard(vo);
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(@RequestParam Integer seq, Model m) {
	    BoardVO vo = new BoardVO();
	    vo.setSeq(seq);
	    System.out.println("getBoard 값 : " + vo.toString());
	    m.addAttribute("board", boardService.getBoard(vo));
	    return "/board/getBoard";
	}
    
    @PostMapping("/updateBoard")
    public String updateBoard(BoardVO vo) {
    	System.out.println("update vo 값 : " + vo.toString());
    	boardService.updateBoard(vo);
    	return "redirect:/board/getBoardList";
    }
}
