package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Drugstore;

@Mapper
public interface DrugstoreDAO {
	List<Drugstore> selectDrugstore();
}
