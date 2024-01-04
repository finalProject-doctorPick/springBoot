package com.example.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.domain.BoardVO;

// Repository == DAO (Model)
public interface BoardRepository extends CrudRepository<BoardVO, Integer>{
	/*
	 * 	Repository
	 * 		- findAll()
	 * 		- findById()
	 * 		- save()
	 * */
}
