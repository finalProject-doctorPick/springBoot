package com.example.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdminDAO;
import com.example.dao.CertificateDAO;
import com.example.dao.DoctorDAO;
import com.example.dao.DrugstoreDAO;
import com.example.dao.HospitalDAO;
import com.example.dao.InquiryDAO;
import com.example.dao.MemberDAO;
import com.example.dao.PaymentDAO;
import com.example.dao.ReservationDAO;
import com.example.domain.DashBoard;
import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Hospital;
import com.example.domain.Inquiry;
import com.example.domain.Member;
import com.example.domain.ServerResponse;
import com.example.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminDAO adminDAO;
	private final HospitalDAO hospitalDAO;
	private final DrugstoreDAO drugstoreDAO;
	private final PaymentDAO paymentDAO;
	private final DoctorDAO doctorDAO;
	private final InquiryDAO inquiryDAO;
	private final ReservationDAO reservationDAO;
	private final CertificateDAO certificateDAO;
	private final MemberDAO memberDAO;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-19
     *  @param		: String searchKeyword
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 회원 목록 조회
     * */
	@Transactional(readOnly = true)
	public List<?> getMemberList() {
		List<Member> response = adminDAO.getMemberList();
		return response;
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 의사 전체 목록 조회 
	 * */
	@Transactional(readOnly = true)
	public List<Doctor> getDoctorsList() {
		return doctorDAO.getDoctorsList();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 의사 등록 요청 수
	 * */
	@Transactional(readOnly = true)
	public int getDoctorRequestCnt() {
		return adminDAO.getDoctorRequestCnt();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 등록 요청 의사 목록
	 * */
	@Transactional(readOnly = true)
	public List<Doctor> getRegistRequestList() {
		return doctorDAO.getRegistRequestList();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 의사 문의 목록
	 * */
	@Transactional(readOnly = true)
	public List<Inquiry> getDoctorInquiryList() {
		return inquiryDAO.getDoctorInquiryList(0);
	}
	
	/**
     * 	@author 	: 정하림
     *  @created	: 2024-01-24
     *  @param		: void
     *  @return		: List<DashBoard>
     *  @explain	: 관리자) 대시보드 - 3개월 매출 조회
     * */
	@Transactional(readOnly = true)
	public List<DashBoard> getMonthlySales() {
		return paymentDAO.getMonthlySales();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 병원 관리 - 병원 목록
	 * */
	@Transactional(readOnly = true)
	public List<Hospital> getHospitalList() {
		return hospitalDAO.getHospitalList();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 약국 관리 - 약국 목록
	 * */
	@Transactional(readOnly = true)
	public List<Drugstore> getDrugstoreList() {
		return drugstoreDAO.getDrugstoreList();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 회원 문의 목록
	 * */
	@Transactional(readOnly = true)
	public List<Inquiry> getMemberInquiryList() {
		return inquiryDAO.getMemberInquiryList(0);
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 약국 문의 목록
	 * */
	@Transactional(readOnly = true)
	public List<Inquiry> getDrugstoreInquiryList() {
		return inquiryDAO.getDrugstoreInquiryList(0);
	}

	/**
     * 	@author 	: 백두산
     *  @created	: 2024-01-30
     *  @param		: Inquiry inquiryData
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 문의 답변
     * */
	@Transactional
	public ResponseEntity<?> updateInquiryAnswer(Inquiry inquiryData) {
		ServerResponse response = new ServerResponse();
		int updateYn = inquiryDAO.updateInquiryAnswer(inquiryData);
		
		if(updateYn == 0) {
			response.setSuccess(false);
			response.setMessage("문의 답변 등록에 실패했습니다.");
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			response.setSuccess(true);
			response.setMessage("문의 답변 등록을 하였습니다.");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		
	}

	@Override
	public List<Inquiry> getMemberInquiryListByDate(Map<String, String> date) {
		return inquiryDAO.getMemberInquiryListByDate(date);
	}

	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Doctor
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 의사 정보 수정
	 * */
	public Integer updateDoctorsInfo(Doctor entry) {
		return doctorDAO.updateDoctorsInfo(entry);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-02-01
	 *  @param      : String
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 등록 요청 승인
	 * */
	@Override
	public int updateDoctorRegister(String doctorEmail) {
		int result = doctorDAO.updateDoctorRegister(doctorEmail);
		return result;
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 당일 총 예약 건수 조회
	 * */
	@Override
	public int getReservationCnt() {
		return reservationDAO.getReservationCnt();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 당일 총 진료 건수 조회
	 * */
	@Override
	public Integer getCertificateCnt() {
		return certificateDAO.getCertificateCnt();
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 당일 신규 회원 수 조회
	 * */
	@Override
	public Integer getNewUserCnt() {
		return memberDAO.getNewUserCnt();
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 올해 신규 회원 수 조회
	 * */
	@Override
	public Integer getNewUserCntByYear() {
		return memberDAO.getNewUserCntByYear();
	}

	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Hospital
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 병원 관리 - 병원 정보 수정
	 * */
	public Integer updateHospitalInfo(Hospital entry) {
		return hospitalDAO.updateHospitalInfo(entry);
	}

	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Drugstore
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 약국 관리 - 약국 정보 수정
	 * */
	public Integer updateDrugstoreInfo(Drugstore entry) {
		System.out.println("impl dugstore객체:"+entry);
		return drugstoreDAO.updateDrugstoreInfo(entry);
	}
}
