package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MemberDAO;
import com.example.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public Member login(Member m) {
		return memberDAO.login(m);
	}

	@Override
	public void insertMember(Member m) {
		memberDAO.insertMember(m);
	}
	
}
