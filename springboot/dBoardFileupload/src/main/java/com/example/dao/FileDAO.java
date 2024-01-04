package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.FileVO;
import com.example.domain.Member;

@Mapper
public interface FileDAO {

	public void insertFile(FileVO vo);
	public FileVO selectFile(FileVO vo);
	public long selectId();
}
