package com.example.service;

import java.util.List;

import com.example.domain.Board;

public interface BoardService {
	List<Board> getBoardList();

	Board getBoard(int seq);

	void saveBoard(Board vo);
}
