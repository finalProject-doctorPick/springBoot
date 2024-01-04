package com.example.service;

import java.util.Optional;

import com.example.domain.Member;

public interface MemberService {

	Optional<Member> getMember(Integer userId);
	
	Member addMember(Member member);

	Member findByEmail(String member_email);


}
