package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Doctor;
import com.example.domain.DoctorAvail;
import com.example.domain.Member;
import com.example.domain.MemberHistory;
import com.example.domain.Review;
import com.example.dto.DoctorDTO;

@Mapper
public interface DoctorDAO {
	void registerDoctor(DoctorDTO dto);

	// 의사 조회 (이메일)
	public Doctor findDoctorByEmail(String userEmail);

	// 의사 진료 조회
	public List<Member> getDoctorCurrentHistory(Integer doctorId);

	// 진료 상세보기 조회
	public MemberHistory getDetailedHistory(Integer certificateNum);
	
	// 진료) 의사목록 조회
	public List<?> getDoctorClinicList(String doctorSubject);
	
	// 진료) 의사상세 리뷰 
	public List<?> getDoctorReview(Integer doctorId);
	
	// 의사 진료시간 정보 조회
	public List<DoctorAvail> getDoctorAvailList(String doctorEmail);

	// 의사 정보 조회
	public Doctor getDoctorInfoList(String doctorEmail);
	
	/******************** 관리자 ********************/
	
	// 관리자) 의사 등록 요청 수
	public int getDoctorRequestCnt();
	
	// 관리자) 의사 전체 조회
	public List<Doctor> getDoctorsList();
	
	// 관리자) 등록 전 의사 전체 조회
	public List<Doctor> getRegistRequestList();

	// 관리자) 의사 문의 목록 조회
	public List<Member> getDoctorInquiryList();
	
	// 관리자) 의사 등록 요청 승인
	public int updateDoctorRegister(String doctorEmail);

	// 관리자 - 의사 정보 수정
	Integer updateDoctorsInfo(Doctor entry);
	
	/******************** 의사 ********************/
	
	// 의사 회원가입 - 진료시간 기본 설정
	public int saveAvailInfo(Integer doctorId);

	// 환자 진료내역 조회
	List<MemberHistory> getPatientDetail(Integer memberId);

	Integer reservationCntForDoctor(Integer doctorId);

	Integer reservationWaitCntForDoctor(Integer doctorId);

	Integer unpaidPaymentSum(Integer doctorId);

	Integer totalSales(Integer doctorId);

	Integer reviewsCnt(Integer doctorId);

	Integer reviewAvg(Integer doctorId);

	List<Review> getRecentReviewsList(Integer doctorId);

	List<Doctor> getDoctorSubjectCntList();

}
