package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Member;
import com.example.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{
	Optional<Member> findByMemberEmail(String email);

	boolean existsByMemberEmail(String email);

}