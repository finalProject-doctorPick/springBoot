package com.example.dao;

import javax.transaction.Transactional;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Files;

@Mapper
public interface FilesDAO {

	void registerFile(Files registerFile); 
	
}
