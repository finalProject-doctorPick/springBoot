package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Certificate;
import com.example.domain.Doctor;
import com.example.domain.DoctorAvail;
import com.example.domain.Inquiry;
import com.example.domain.MemberHistory;
import com.example.domain.Review;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.DoctorEntity;

public interface DoctorService {
	
	/**
	 *	*******************JPA*********************** 
	 * */
	// 의사 이메일 체크
	boolean existsByDoctorEmail(String email);

	// 의사 등록
	ServerResponse registerDoctor(Users userSignupDTO, List<MultipartFile> fileList);

	// 의사 정보 조회
	DoctorEntity getDoctor(String email);

	/**
	 *	****************MyBatis********************* 
	 * */
	// 의사 조회
	Doctor validateDoctorEmailAndPwd(String email, String pwd);

	// 의사 진료 목록 조회
	List<?> getDoctorCurrentHistory(Integer doctorId);

	// 특정 진료 상세보기 조회
	MemberHistory getDetailedHistory(Integer certificateNum);
	
	// 진료) 의사 목록 조회
	List<?> getDoctorClinicList(String doctorSubject);
	
	// 진료) 의사 상세 - 리뷰
	List<?> getDoctorReview(Integer doctorId);

	// 의사 비대면진료 목록 조회 (접수대기/진료목록/진료종료) 
	Map<String, List<?>> getDoctorNonFaceToFaceList(Integer doctorId);
	
	// 의사 진료시간 조회
	List<DoctorAvail> getDoctorAvailList(String doctorEmail);

	// 의사 정보 조회
	Doctor getDoctorInfoList(String doctorEmail);
	
	// 의사 문의 조회
	List<Inquiry> getDoctorInquiryList(Integer doctorId);

	// 환자 진료내역 조회
	Map<String, List<?>> getPatientDetail(Integer memberId);
	
	// 의사) 대시보드 - 예약 건수 조회
	Integer reservationCntForDoctor(Integer doctorId);

	Integer reservationWaitCntForDoctor(Integer doctorId);

	Integer unpaidPaymentSum(Integer doctorId);

	Integer totalSales(Integer doctorId);

	Integer reviewsCnt(Integer doctorId);

	Integer reviewAvg(Integer doctorId);

	// 진료 등록
	ResponseEntity<?> registCertificate(Integer reservationNum);

	// 환자 입장요청 SMS 전송
	ResponseEntity<?> callSMSSendToPatient(Integer memberId);

	// 환자 예약 취소
	ResponseEntity<?> cancelReservation(Integer reservationNum, Integer memberId);

	// 환자 진료 취소
	ResponseEntity<?> cancelCertification(Integer certificateNum, Integer memberId);

	List<Review> getRecentReviewsList(Integer doctorId);

	List<Doctor> getDoctorSubjectCntList();

	// 진료 종료
	ResponseEntity<?> finishCertificate(Certificate certificateData, List<MultipartFile> certificateFile,
			List<MultipartFile> prescriptionFile);

}
