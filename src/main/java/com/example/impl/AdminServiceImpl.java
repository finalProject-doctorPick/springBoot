package com.example.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdminDAO;
import com.example.dao.DoctorDAO;
import com.example.dao.DrugstoreDAO;
import com.example.dao.HospitalDAO;
import com.example.dao.InquiryDAO;
import com.example.dao.MemberDAO;
import com.example.dao.PaymentDAO;
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
	private final MemberDAO memberDAO;
	private final InquiryDAO inquiryDAO;
	
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
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-22
     *  @param		: void
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 문의 목록 조회
     * */
	@Transactional(readOnly = true)
	public List<?> getInquiryList(String userEmail) {
		List<Inquiry> response = adminDAO.getInquiryList(userEmail);
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
	public List<Member> getDoctorInquiryList() {
		return adminDAO.getDoctorInquiryList();
	}
	
	/**
     * 	@author 	: 정하림
     *  @created	: 2024-01-24
     *  @param		: 
     *  @return		: List<DashBoard>
     *  @explain	: 관리자) 대시보드 - 월 매출 조회
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
		return memberDAO.getMemberInquiryList("");
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 약국 문의 목록
	 * */
	@Transactional(readOnly = true)
	public List<Drugstore> getDrugstoreInquiryList() {
		return drugstoreDAO.getDrugstoreInquiryList();
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
}
