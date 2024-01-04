package com.example.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.Board;
import com.example.service.BoardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
/*
    @Controller - 뷰 페이지 지정해야 함
                            ( jsp / thymeleaf  응답을 주는 경우)
                            * ajax 인 경우
                                @Controller + @ResponseBody
    @RestContoller == @Controller + @ResponseBody
*/
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Tag(name="BoardController", description = "게시판 관련 생성")
public class BoardController {
	
    @Autowired
    private BoardService boardService;
    
    @GetMapping("/{step}")
    @Operation(summary = "hello요청", description = "함수 설명")
    public String viewPage(@PathVariable String step) {
        return "/board/" + step;
    }
    @GetMapping("/hello")
    public String hello() {
        return "REST 컨트롤러";
    }
    // 글목록 검색
    @GetMapping("/board")
    public List<Board> getBoardList(Board v) {
        Board vo = new Board();
        List<Board> list = boardService.getBoardList();
        return list;
    }
    // 글 상세보기
    @GetMapping("/board/{seq}")
    public Board getBoard(@PathVariable Integer seq) {
        Board vo = new Board();
        vo.setSeq(seq);
        Board result = boardService.getBoard(seq);
        return result;
    }
    @PostMapping("/board/write")
    public void insertBoard(Board vo) {
        boardService.saveBoard(vo);
    }
    
    @DeleteMapping("/board/{seq}")
    public void deleteBoard(@PathVariable Integer seq) {
    	System.out.println(seq + "번 글을 지웁니다.");
    }
}