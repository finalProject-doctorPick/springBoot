package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	boolean existsByDoctorEmail(String email);
}
