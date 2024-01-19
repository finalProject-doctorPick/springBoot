package com.example.service;

import java.util.List;

import com.example.domain.Member;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.MemberEntity;

public interface MemberService {

	// 이메일 존재 유무
	boolean existsByMemberEmail(String email);
	
	// 회원 등록
	ServerResponse registerMember(Users saveData);

	// 회원 조회
	Member findByMemberEmail(String email, String pwd);

	
	// 회원 조회
	MemberEntity getMember(String email);

	// 회원 기록 조회
	List<?> getMemberCurrntHistory(Integer memberId);
	
}