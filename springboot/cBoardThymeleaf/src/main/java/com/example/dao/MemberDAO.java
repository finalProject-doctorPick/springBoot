package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface MemberDAO {
	public Member login(Member m);
	public void insertMember(Member m);
}
