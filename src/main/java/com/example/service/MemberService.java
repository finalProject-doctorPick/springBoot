package com.example.service;

import java.util.Optional;

import com.example.domain.Member;

public interface MemberService {

	// 회원 조회
	Optional<Member> getMember(Integer userId);
	
	// 회원 저장
	Member addMember(Member member);

	Member findByEmail(String email);

}
