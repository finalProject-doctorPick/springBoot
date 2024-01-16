package com.example.service;

import com.example.domain.Member;
import com.example.domain.ServerResponse;
import com.example.domain.Users;

public interface MemberService {

	// 이메일 존재 유무
	boolean existsByMemberEmail(String email);
	
	// 회원 등록
	ServerResponse registerMember(Users saveData);

	// 회원 조회
	Member findByMemberEmail(String email, String pwd);
	
}