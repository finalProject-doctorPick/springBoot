package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.Member;
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

	// 의사 등록 요청 수 조회
	int getDoctorRequestCnt();

	// 의사 전체 조회
	List<Doctor> getDoctorsList();

	// 의사 등록 요청(한 의사) 목록 조회
	List<Doctor> getRegistRequestList();

	// 의사 진료 목록 조회
	List<?> getDoctorCurrentHistory(Integer doctorId);

	// 특정 진료 상세보기 조회
	MemberHistory getDetailedHistory(Integer certificateNum);

	// 관리자) 의사 문의 조회
	List<Member> getDoctorInquiryList();

	//id로 의사 이메일 찾기
	String getDoctorEmailFromId(Integer doctorId);
	
	// 진료) 의사 목록 조회
	List<?> getDoctorClinicList(String doctorSubject);
	
	// 진료) 의사 상세 - 리뷰
	List<?> getDoctorReview(Integer doctorId);
	
	
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
