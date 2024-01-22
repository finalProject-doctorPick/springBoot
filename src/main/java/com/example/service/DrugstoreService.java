package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Drugstore;

import com.example.domain.DrugstoreHistory;

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
	/**
	 *	*******************JPA*********************** 
	 * */
	// 약국 이메일 확인
	boolean existsByDrugstoreEmail(String email);

	// 약국 등록
	ServerResponse registerDrugstore(Users userSignupDTO, List<MultipartFile> fileList);

	// 약국 회원 정보 조회
	DrugstoreEntity getDrugstore(String email);
}
