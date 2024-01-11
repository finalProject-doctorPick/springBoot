package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserSignupDTO;

public interface DoctorService {

	// 의사 이메일 체크
	boolean existsByDoctorEmail(String email);

	// 의사 등록
	Object registerDoctor(UserSignupDTO userSignupDTO, List<MultipartFile> fileList);

}
