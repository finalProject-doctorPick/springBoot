package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.MemberDTO;

public interface MemberRepository extends JpaRepository<MemberDTO, Integer>{
	Optional<MemberDTO> findByMemberEmail(String email);

	boolean existsByMemberEmail(String email);
	
}