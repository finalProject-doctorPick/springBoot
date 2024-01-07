package com.example.service;

import java.util.Optional;

import com.example.domain.Member;

public interface MemberService {

	// 회원 조회 (회원ID)
	Optional<Member> getMember(Integer userId);
	
	// 회원 조회 (이메일)
	Optional<Member> getMember(String email);
	
	// 회원 저장
	Member addMember(Member member);

	// 이메일 조회
	Member findByEmail(String email);
}