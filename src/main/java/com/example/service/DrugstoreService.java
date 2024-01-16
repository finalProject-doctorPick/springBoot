package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Drugstore;
import com.example.domain.Users;
import com.example.domain.ServerResponse;

public interface DrugstoreService {

	// 약국 이메일 확인
	boolean existsByDrugstoreEmail(String email);

	// 약국 등록
	ServerResponse registerDrugstore(Users userSignupDTO, List<MultipartFile> fileList);

	// 약국 조회
	Drugstore findByDrugstoreEmail(String email, String pwd);

}
