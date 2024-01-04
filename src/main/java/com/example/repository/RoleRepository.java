package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	 Optional<Role> findByMemberName(String name);
}
