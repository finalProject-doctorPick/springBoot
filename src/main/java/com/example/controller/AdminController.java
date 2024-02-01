package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DashBoard;
import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Hospital;
import com.example.domain.Inquiry;
import com.example.domain.Member;
import com.example.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-19
     *  @param		: String searchKeyword
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 회원 목록 조회
     * */
    @GetMapping("/getMemberList")
    public ResponseEntity<?> getMemberList(){
    	List<?> list = adminService.getMemberList();
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 의사 전체 목록 조회 
	 * */
	@GetMapping("/getDoctorsList")
	public ResponseEntity<?> getDoctorsList(){
		List<Doctor> list = adminService.getDoctorsList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 의사 등록 요청 수
	 * */
	@GetMapping("/getDoctorRequestCnt")
	public ResponseEntity<Integer> getDoctorRequestCnt() {
		int requestCnt = adminService.getDoctorRequestCnt();
		return new ResponseEntity<>(requestCnt, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 월 매출
	 * */
	@GetMapping("/getMonthlySales")
	public ResponseEntity<?> getMonthlySales() {
		List<DashBoard> list = adminService.getMonthlySales();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 등록 요청 의사 목록
	 * */
	@GetMapping("/getRegistRequestList")
	public ResponseEntity<?> getRegistRequestList() {
		List<Doctor> list = adminService.getRegistRequestList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 병원 관리 - 병원 목록
	 * */
	@GetMapping("/getHospitalList")
	public ResponseEntity<?> getHospitalList() {
		List<Hospital> list = adminService.getHospitalList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 약국 관리 - 약국 목록
	 * */
	@GetMapping("/getDrugstoreList")
	public ResponseEntity<?> getDrugstoreList() {
		List<Drugstore> list = adminService.getDrugstoreList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      :
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 회원 문의 목록
	 * */
	@GetMapping("/getMemberInquiryList")
	public ResponseEntity<?> getMemberInquiryList() {
		List<Inquiry> list = adminService.getMemberInquiryList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 의사 문의 목록
	 * */
	@GetMapping("/getDoctorInquiryList")
	public ResponseEntity<?> getDoctorInquiryList() {
		List<Member> list = adminService.getDoctorInquiryList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 약국 문의 목록
	 * */
	@GetMapping("/getDrugstoreInquiryList")
	public ResponseEntity<?> getDrugstoreInquiryList() {
		List<Inquiry> list = adminService.getDrugstoreInquiryList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
     * 	@author 	: 백두산
     *  @created	: 2024-01-30
     *  @param		: Inquiry inquiryData
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 문의 답변
     * */
	@PostMapping("/updateInquiryAnswer")
	public ResponseEntity<?> updateInquiryAnswer(@RequestBody Inquiry inquiryData){
		ResponseEntity<?> response = adminService.updateInquiryAnswer(inquiryData);
    	return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Doctor
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 의사 정보 수정
	 * */
	@PostMapping("/updateDoctorsInfo")
	public ResponseEntity<?> updateDoctorsInfo(@RequestBody Doctor entry){
		return new ResponseEntity<>(adminService.updateDoctorsInfo(entry), HttpStatus.OK);
	}
	
	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Hospital
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 병원 관리 - 병원 정보 수정
	 * */
	@PostMapping("/updateHospitalInfo")
	public ResponseEntity<?> updateHospitalInfo(@RequestBody Hospital entry){
		return new ResponseEntity<>(adminService.updateHospitalInfo(entry), HttpStatus.OK);
	}
	

	/**
	 * 	@author     : 박병태
	 *  @created    : 2024-02-1
	 *  @param      : Dcotor
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 병원 관리 - 병원 정보 수정
	 * */
	@PostMapping("/updateDrugstoreInfo")
	public ResponseEntity<?> updateDrugstoreInfo(@RequestBody Drugstore entry){
		System.out.println("drugstore 객체:"+entry.toString());
		return new ResponseEntity<>(adminService.updateDrugstoreInfo(entry), HttpStatus.OK);
	}
}
