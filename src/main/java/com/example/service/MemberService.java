package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.DashBoard;
import com.example.domain.Member;
import com.example.domain.Reservation;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.MemberEntity;

public interface MemberService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 일반 회원 조회 및 비밀번호 검증
	Member findByMemberEmailAndPwd(String email, String pwd);
	
	// 회원 기록 조회
	List<?> getMemberCurrentHistory(Integer memberId);
	
	// 회원 리뷰 조회
	List<?> getMemberReview(Integer memberId);
	
	// 통계 - 나이대별 회원 조회
	List<DashBoard> getMembersCntByAge();
	
	// 관리자) 회원 문의 조회 
	List<Member> getMemberInquiryList();
	
	// 일반회원 정보 조회
	Member findMemberByEmail(String memberEmail);
	
	
	void registReservation(Reservation reservationData,  List<MultipartFile> fileList);
	
	/**
	 *	*******************JPA*********************** 
	 * */
	// 이메일 존재 유무
	boolean existsByMemberEmail(String email);
	
	// 일반 회원 등록
	ServerResponse registerMember(Users saveData);
	
	// 일반 회원 조회
	MemberEntity getMember(String email);


	

}