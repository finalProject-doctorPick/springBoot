package com.example.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.domain.BoardVO;

public interface QueryAnnoRepository extends CrudRepository<BoardVO, Integer>{

	/*
	 * 	***********************************
	 * 	[1] 복잡한 쿼리 - JPQL (*** SQL 문장 아님)
	 * 		테이블명이 아니고, BoardVO 클래스명
	 
	@Query(" SELECT b "
			+ " FROM BoardVO b "
			+ " WHERE b.title like %?1% "
			+ " ORDER BY b.seq DESC ")
	List<BoardVO> queryAnno(String word);
	*/
	
	/*
	 * 	***********************************
	 * 	[2] 복잡한 쿼리 - SQL 문장
	*/ 
	@Query(value="select * from board where title like '%?1%' order by seq desc", nativeQuery = true)
	List<BoardVO> queryAnno(String word);
	
}
