package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{
	boolean existsByMemberEmail(String email);
	
	MemberEntity findByMemberEmail(String email);


}