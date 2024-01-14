package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.UserRequest;
import com.example.domain.UserResponse;

public interface DoctorService {

	// 의사 이메일 체크
	boolean existsByDoctorEmail(String email);

	// 의사 등록
	UserResponse registerDoctor(UserRequest userSignupDTO, List<MultipartFile> fileList);

	// 의사 조회
	Doctor findByDoctorEmail(String email, String pwd);

}
