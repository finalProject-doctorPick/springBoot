package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.DashBoard;
import com.example.domain.Inquiry;
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
	public Member findByMemberEmailAndPwd(String email, String pwd);
	
	// 회원 기록 조회
	public List<?> getMemberCurrentHistory(Integer memberId);
	
	// 회원 리뷰 조회
	public List<?> getMemberReview(Integer memberId);
	
	// 통계 - 나이대별 회원 조회
	public List<DashBoard> getMembersCntByAge();
	
	// 일반회원 정보 조회
	public Member findMemberByEmail(String memberEmail);

	// 일반회원 정보 수정
	public ResponseEntity<?> updateMemberInfo(Member updateMemberData);

	// 일반회원 문의 조회
	public List<Inquiry> getMemberInquiryList(String userEmail);

	// 예약 등록
	public void registReservation(Reservation reservationData,  List<MultipartFile> fileList);
	
	// 일반회원 리뷰 삭제
	public ResponseEntity<?> deleteReviewId(List<Integer> reviewList);
	
	/**
	 *	*******************JPA*********************** 
	 * */
	// 이메일 존재 유무
	public boolean existsByMemberEmail(String email);
	
	// 일반 회원 등록
	public ServerResponse registerMember(Users saveData);
	
	// 일반 회원 조회
	public MemberEntity getMember(String email);



	

}