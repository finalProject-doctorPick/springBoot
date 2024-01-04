package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Board;

/* ***************************************
 * Spring-Boot 에서는 BoardDAOImpl 클래스 생성 안함
 *  ---> 자동으로 Mapper 연동
 *  
 *  	[spring] 
 *  	@Repository
 *  	class BoardDAOImpl implements BoardDAO{ ... }
 *  	
 *  	[spring-boot]
 *  	@Mapper
 *  	interface BoardDAO { ... }
 *  		ㄴ 경로는 src/main/resources/.../xxx.xml
 * */
@Mapper
public interface BoardDAO {
	public List<Board> getBoardList();
}


