package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface MemberDAO {
	// 회원 조회
	public Member findByMember(String email);

	// 회원 진료 조회
	public List<Member> getMemberCurrntHistory(Integer memberId);

	// 관리자) 회원 목록 조회
	public List<Member> getMemberListForAdmin(String searchKeyword);

}
