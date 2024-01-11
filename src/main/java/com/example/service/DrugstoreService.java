package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserSignupDTO;

public interface DrugstoreService {

	// 약국 이메일 확인
	boolean existsByDrugstoreEmail(String email);

	// 약국 등록
	Object registerDrugstore(UserSignupDTO userSignupDTO, List<MultipartFile> fileList);

}
