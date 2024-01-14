package com.example.dao;

import com.example.domain.Member;

public interface MemberDAO {

	// 회원 조회
	public Member findByMember(String email);

}
