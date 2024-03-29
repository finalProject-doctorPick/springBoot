package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DashBoard;
import com.example.domain.Inquiry;
import com.example.domain.Member;

@Mapper
public interface MemberDAO {
	// 회원 조회
	public Member findMemberByEmail(String email);

	// 회원 정보 조회
	public List<Member> findMemberById(Integer memberId);
	
	// 회원 진료 조회
	public List<Member> getMemberCurrentHistory(Integer memberId);

	// 관리자) 통계 - 나이대별 회원 조회
	public List<DashBoard> getMembersCntByAge();

	// 회원 리뷰 조회
	public List<?> getMemberReview(Integer memberId);

	// 회원 정보 수정
	public int updateMemberInfo(Member updateMemberData);

	// 회원 문의 조회
	public List<Inquiry> getMemberInquiryList(String userEmail);

	// 관리자) 당일 신규 회원 수 조회
	public Integer getNewUserCnt();

	// 관리자) 올해 신규 회원 수 조회
	public Integer getNewUserCntByYear();

}