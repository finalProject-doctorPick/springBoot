package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface MemberDAO {
	// 회원 조회
	public Member findByMember(String email);

}
