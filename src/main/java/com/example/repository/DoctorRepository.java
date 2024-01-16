package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Doctor;
import com.example.dto.DoctorDTO;
import com.example.entity.DoctorEntity;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer>{
	boolean existsByDoctorEmail(String email);

	Doctor save(DoctorDTO insertDoctor);
}
