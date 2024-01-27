package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.MemberHistory;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.DoctorEntity;

public interface DoctorService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 의사 조회
	Doctor validateDoctorEmailAndPwd(String email, String pwd);

	// 의사 진료 목록 조회
	List<?> getDoctorCurrentHistory(Integer doctorId);

	// 특정 진료 상세보기 조회
	MemberHistory getDetailedHistory(Integer certificateNum);

	//id로 의사 이메일 찾기
	String getDoctorEmailFromId(Integer doctorId);

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
