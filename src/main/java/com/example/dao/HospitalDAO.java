package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Hospital;
import com.example.dto.HospitalDTO;

@Mapper
public interface HospitalDAO {
	
	// 병원 조건 검색
	Hospital selectHospital(HospitalDTO h);
	
	// 병원 전체 검색
	List<Hospital> searchHospital(HospitalDTO h);
	
	// 병원 등록
	void saveHospital(HospitalDTO h);
	
	// 병원 수정
	void updateHospital(HospitalDTO h);
	
	// 병원 삭제
	void deleteHospital(HospitalDTO h);
	
}
