package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Drugstore;
import com.example.domain.Hospital;

@Mapper
public interface DrugstoreDAO {
	List<Drugstore> selectDrugstore();
	
	Drugstore validateDrugstoreEmail(String email);

	List<Drugstore> getDrugstoreList();

	List<Drugstore> getDrugstoreListByKeyword(String keyword);
}
