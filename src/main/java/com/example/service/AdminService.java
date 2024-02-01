package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.domain.DashBoard;
import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Hospital;
import com.example.domain.Inquiry;

public interface AdminService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 관리자) 회원 조회
	public List<?> getMemberList();

	// 관리자) 의사 전체 조회
	public List<Doctor> getDoctorsList();

	// 관리자) 대시보드 - 의사 등록 요청 수
	public int getDoctorRequestCnt();

	// 관리자) 의사 관리 - 등록 요청 의사 목록
	public List<Doctor> getRegistRequestList();
	
	// 관리자) 월 매출 조회
	
	// 관리자) 3개월 매출 조회
	public List<DashBoard> getMonthlySales();

	// 관리자) 병원 목록 조회
	public List<Hospital> getHospitalList();

	// 관리자) 약국 관리 - 약국 목록
	public List<Drugstore> getDrugstoreList();
	
	// 관리자) 의사 문의 조회
	public List<Inquiry> getDoctorInquiryList();

	// 관리자) 회원 문의목록 조회 
	public List<Inquiry> getMemberInquiryList();

	// 관리자) 약국 문의목록 조회
	public List<Inquiry> getDrugstoreInquiryList();

	// 관리자) 문의 답변
	public ResponseEntity<?> updateInquiryAnswer(Inquiry inquiryData);

	// 관리자) 날짜 필터링 - 회원 문의 조회
	public List<Inquiry> getMemberInquiryListByDate(Map<String, String> date);

	// 관리자) 의사정보 수정
	public Integer updateDoctorsInfo(Doctor entry);

	// 관리자) 병원정보 수정
	public Integer updateHospitalInfo(Hospital entry);

	// 관리자) 약국정보 수정
	public Integer updateDrugstoreInfo(Drugstore entry);

	// 관리자) 의사 관리 - 등록 요청 승인
	public int updateDoctorRegister(String doctorEmail);

	// 관리자) 대시보드 - 당일 총 예약 건수 조회
	public int getReservationCnt();

	// 관리자) 대시보드 - 당일 총 진료 건수 조회
	public Integer getCertificateCnt();

	// 관리자) 대시보드 - 당일 신규 회원 수 조회
	public Integer getNewUserCnt();

	// 관리자) 대시보드 - 올해 신규 회원 수 조회
	public Integer getNewUserCntByYear();
}
