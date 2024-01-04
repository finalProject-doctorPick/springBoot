package com.example.service;

import com.example.domain.Member;

public interface MemberService {
	public Member login(Member m);
	public void insertMember(Member m);
}
