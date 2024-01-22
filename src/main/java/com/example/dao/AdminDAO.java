package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface AdminDAO {

	// 관리자) 회원 목록 조회
	public List<Member> getMemberList(String searchKeyword);
}
