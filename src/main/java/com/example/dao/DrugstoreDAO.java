package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Drugstore;
import com.example.domain.DrugstoreHistory;
import com.example.domain.Inquiry;
import com.example.domain.Review;

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

	Integer updateDrugstoreInfo(Drugstore entry);
	
	Integer newOrder(Integer drugstoreId);

	Integer receiveWait(Integer drugstoreId);

	Integer received(Integer drugstoreId);

	Integer totalOrderCnt(Integer drugstoreId);

	Integer deliveryCnt(Integer drugstoreId);

	Integer pickupCnt(Integer drugstoreId);

	List<DrugstoreHistory> getRecentWaitingList(Integer drugstoreId);

}

