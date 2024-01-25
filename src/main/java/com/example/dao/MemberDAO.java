package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DashBoard;
import com.example.domain.Member;

@Mapper
public interface MemberDAO {
	// 회원 조회
	public Member findByMember(String email);

	// 회원 진료 조회
	public List<Member> getMemberCurrentHistory(Integer memberId);

	// 관리자) 통계 - 나이대별 회원 조회
	public List<DashBoard> getMembersCntByAge();

	// 관리자) 문의 관리 - 의사 문의 목록 조회
	public List<Member> getMemberInquiryList();

	// 회원 리뷰 조회
	public List<?> getMemberReview(Integer memberId);

}
