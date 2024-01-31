package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Drugstore;
import com.example.domain.DrugstoreHistory;
import com.example.domain.Inquiry;



@Mapper
public interface DrugstoreDAO {
	List<Drugstore> selectDrugstore();
	
	Drugstore validateDrugstoreEmail(String email);

	List<Drugstore> getDrugstoreList();

	List<Drugstore> getDrugstoreListByKeyword(String keyword);

	List<DrugstoreHistory> getDrugstoreHistoryList(Integer drugstoreId);

	List<Inquiry> getDrugstoreInquiry(Integer drugstoreId);

}

