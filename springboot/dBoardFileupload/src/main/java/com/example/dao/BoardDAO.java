package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Board;

@Mapper
public interface BoardDAO {
	public List<Board> getBoardList();

	public Board getBoard(int seq);

	public void saveBoard(Board vo);
}


