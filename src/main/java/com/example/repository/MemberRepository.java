package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{
	Optional<Member> findByMemberEmail(String email);
}
