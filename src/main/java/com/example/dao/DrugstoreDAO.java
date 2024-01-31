package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;

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

	int updateDrugstoreHistory(DrugstoreHistory storeHistory);
	// 관리자) 문의 관리 - 약국 문의 목록
	List<Drugstore> getDrugstoreInquiryList();

	List<Inquiry> getDrugstoreInquiry(Integer drugstoreId);


}

