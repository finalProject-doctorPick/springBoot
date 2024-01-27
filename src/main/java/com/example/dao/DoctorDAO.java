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

	// 의사 조회 (이메일)
	public Doctor findDoctorByEmail(String userEmail);

	// 의사 이메일 id에서 찾기
	public String getDoctorEmail(Integer doctorId);

	// 의사 진료 조회
	public List<Member> getDoctorCurrentHistory(Integer doctorId);

	// 진료 상세보기 조회
	public MemberHistory getDetailedHistory(Integer certificateNum);

	// 관리자) 의사 전체 조회
	public List<Doctor> getDoctorsList();
	
	// 관리자) 등록 전 의사 전체 조회
	public List<Doctor> getRegistRequestList();
}
