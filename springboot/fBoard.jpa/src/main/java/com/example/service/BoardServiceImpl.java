package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.BoardVO;
import com.example.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardRepository boardRepository;
	
	// 전체 검색
	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return (List<BoardVO>)boardRepository.findAll();
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		return boardRepository.findById(vo.getSeq()).get();
	}

	@Override
	public void saveBoard(BoardVO vo) {
		boardRepository.save(vo);
	}
	
	@Override
	public void updateBoard(BoardVO vo) {
		boardRepository.save(vo);
	}
	
}
