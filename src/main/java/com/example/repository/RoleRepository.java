package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.RoleDTO;

public interface RoleRepository extends JpaRepository<RoleDTO, Integer> {
	 Optional<RoleDTO> findByRoles(String name);
}