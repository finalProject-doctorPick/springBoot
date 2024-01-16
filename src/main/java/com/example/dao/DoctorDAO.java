package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Doctor;
import com.example.dto.DoctorDTO;

@Mapper
public interface DoctorDAO {
	void registerDoctor(DoctorDTO dto);

	Doctor findDoctorByEmail(String email);
}
