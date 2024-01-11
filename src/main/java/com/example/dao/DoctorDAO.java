package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Doctor;
import com.example.dto.DoctorSignupDTO;

@Mapper
public interface DoctorDAO {
	Doctor registerDoctor(DoctorSignupDTO dto);
}
