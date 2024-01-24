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

	//의사 이메일 id에서 차지기
	String getDoctorEmail(Integer doctorId);

	// 의사 진료 조회
	List<Member> getDoctorCurrentHistory(Integer doctorId);

	// 관리자  - 의사 등록 요청 수
	int getDoctorRequestCnt();

	// 의사 전체 조회
	List<Doctor> getDoctorsList();

	// 등록 전 의사 전체 조회
	List<Doctor> getRegistRequestList();

	// 진료 상세보기 조회
	MemberHistory getDetailedHistory(Integer certificateNum);

}
