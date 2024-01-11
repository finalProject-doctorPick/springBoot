package com.example.service;

import java.util.Optional;

import com.example.domain.Member;
import com.example.dto.UserSignupDTO;
import com.example.dto.UserSignupResponseDTO;

public interface MemberService {

	// 회원 조회 (회원ID)
	Optional<Member> getMember(Integer userId);
	
	// 회원 조회
	Optional<Member> getMember(String email);
	
	// 회원 등록
	Member addMember(Member member);

	// 이메일 조회
	Member findByEmail(String email);

	
	// **************************************************
	// 이메일 존재 유무
	boolean existsByMemberEmail(String email);
	
	// 회원 등록
	UserSignupResponseDTO registerMember(UserSignupDTO saveData);
	
}