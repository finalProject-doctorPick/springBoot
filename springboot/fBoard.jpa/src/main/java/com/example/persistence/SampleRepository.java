package com.example.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.BoardVO;

public interface SampleRepository extends CrudRepository<BoardVO, Integer>{

	List<BoardVO> findByTitle(String title);
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByCntIsNull();
	List<BoardVO> findByCnt(Integer cnt);
	List<BoardVO> findByTitleOrWriter(String title, String writer);
//	List<BoardVO> findByContainingWriter(String writer);
}
