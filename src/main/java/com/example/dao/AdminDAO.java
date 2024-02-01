package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface AdminDAO {

	// 관리자) 회원 목록 조회
	public List<Member> getMemberList();

	// 관리자) 의사 등록 요청 수
	public int getDoctorRequestCnt();
	
	// 관리자) 의사 문의 목록 조회
	public List<Member> getDoctorInquiryList();
	
}
