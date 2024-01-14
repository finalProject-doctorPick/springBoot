package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.DoctorDTO;

public interface DoctorRepository extends JpaRepository<DoctorDTO, Integer>{
	boolean existsByDoctorEmail(String email);
}
