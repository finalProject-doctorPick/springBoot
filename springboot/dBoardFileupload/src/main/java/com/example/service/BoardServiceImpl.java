package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.BoardDAO;
import com.example.dao.FileDAO;
import com.example.domain.Board;
import com.example.domain.FileVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public List<Board> getBoardList() {
		return boardDAO.getBoardList();
	}

	@Override
	public Board getBoard(int seq) {
		return boardDAO.getBoard(seq);
	}

	@Override
	@Transactional
	public void saveBoard(Board vo, FileVO f) {
		if(f != null) {
			// 파일 첨부가 있는 경우
			fileDAO.insertFile(f);
			vo.setFileId(fileDAO.selectId());
		}
		boardDAO.saveBoard(vo);
	}

}
