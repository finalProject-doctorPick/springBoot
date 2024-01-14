package com.example.service;

import java.util.Optional;

import com.example.domain.Member;
import com.example.domain.UserRequest;
import com.example.domain.UserResponse;
import com.example.dto.MemberDTO;

public interface MemberService {

	// 이메일 존재 유무
	boolean existsByMemberEmail(String email);
	
	// 회원 등록
	UserResponse registerMember(UserRequest saveData);

	// 회원 조회
	Member findByMemberEmail(String email, String pwd);

	MemberDTO findByEmail(String memberEmail);

	Optional<MemberDTO> getMember(Integer userId);
	
}