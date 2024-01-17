package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.Users;
import com.example.entity.DoctorEntity;
import com.example.domain.ServerResponse;

public interface DoctorService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 의사 조회
	Doctor validateDoctorEmailAndPwd(String email, String pwd);
	
	/**
	 *	*******************JPA*********************** 
	 * */
	// 의사 이메일 체크
	boolean existsByDoctorEmail(String email);

	// 의사 등록
	ServerResponse registerDoctor(Users userSignupDTO, List<MultipartFile> fileList);

	// 의사 정보 조회
	DoctorEntity getDoctor(String email);
}
