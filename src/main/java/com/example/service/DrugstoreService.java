package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Drugstore;

import com.example.domain.DrugstoreHistory;
import com.example.domain.Inquiry;
import com.example.domain.Review;
import com.example.domain.Users;
import com.example.entity.DrugstoreEntity;
import com.example.domain.ServerResponse;

public interface DrugstoreService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 약국 정보 조회
	Drugstore validateDrugstoreEmail(String email, String pwd);

	// 약국 리스트 조회
	List<Drugstore> getDrugstoreList();
	
	// 약국 키워드 검색
	List<Drugstore> getDrugstoreListByKeyword(String keyword);

	// 약국 히스토리 조회
	List<DrugstoreHistory> getDrugstoreHistoryList(Integer drugstoreId);

	// 약국 문의 목록 조회
	List<Inquiry> getDrugstoreInquiry(Integer drugstoreId);

	// 약국 정보 수정
	ResponseEntity<?> updateDrugstoreInfo(Drugstore storeData);
	
	// 약국 수령 확인
	ResponseEntity<?> updateDrugstoreHistory(DrugstoreHistory storeHistory);
	/**
	 *	*******************JPA*********************** 
	 * */
	// 약국 이메일 확인
	boolean existsByDrugstoreEmail(String email);

	// 약국 등록
	ServerResponse registerDrugstore(Users userSignupDTO, List<MultipartFile> fileList);

	// 약국 회원 정보 조회
	DrugstoreEntity getDrugstore(String email);

	Integer newOrder(Integer drugstoreId);

	Integer receiveWait(Integer drugstoreId);

	Integer received(Integer drugstoreId);

	Integer totalOrderCnt(Integer drugstoreId);

	Integer deliveryCnt(Integer drugstoreId);

	Integer pickupCnt(Integer drugstoreId);

	List<DrugstoreHistory> getRecentWaitingList(Integer drugstoreId);


}
