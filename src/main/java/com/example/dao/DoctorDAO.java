package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Doctor;
import com.example.domain.Member;
import com.example.domain.MemberHistory;
import com.example.dto.DoctorDTO;

@Mapper
public interface DoctorDAO {
	void registerDoctor(DoctorDTO dto);

	Doctor findDoctorByEmail(String userEmail);


	// 의사 진료 조회
	List<Member> getDoctorCurrentHistory(Integer doctorId);

	// 진료 상세보기 조회
	MemberHistory getDetailedHistory(Integer certificateNum);

}
