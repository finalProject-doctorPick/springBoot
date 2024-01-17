package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
	 Optional<RoleEntity> findByRoles(String name);
}