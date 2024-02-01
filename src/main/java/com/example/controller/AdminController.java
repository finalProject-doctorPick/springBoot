package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.DashBoard;
import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Hospital;
import com.example.domain.Inquiry;
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
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 전체 데이터 조회
	 * */
	@GetMapping("/getDashBoardData")
	public ResponseEntity<?> getDashBoardData(){
		Map<String, Integer> data = new HashMap<>();
		// 당일 예약 건수
	    Integer reservationCnt = adminService.getReservationCnt();
	    // 의사 등록 요청 수
	    Integer requestCnt = adminService.getDoctorRequestCnt();
	    // 당일 진료 건수
	    Integer certificateCnt = adminService.getCertificateCnt();
	    // 당일 신규 회원 수
	    Integer newUserCnt = adminService.getNewUserCnt();
	    // 올해 신규 회원 수
	    Integer newUserCntByYear = adminService.getNewUserCntByYear();
	    
	    data.put("reservationCnt", reservationCnt);
	    data.put("requestCnt", requestCnt);
	    data.put("certificateCnt", certificateCnt);
	    data.put("newUserCnt", newUserCnt);
	    data.put("newUserCntByYear", newUserCntByYear);
	    
	    
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-24
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 3개월 매출
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
	 *  @created    : 2024-02-01
	 *  @param      : void
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 등록 요청 의사 승인
	 * */
	@PostMapping("/updateDoctorRegister")
	public ResponseEntity<?> updateDoctorRegister(@RequestBody Map<String, String> request) {
	    String doctorEmail = request.get("doctorEmail");
	    int result = adminService.updateDoctorRegister(doctorEmail);

	    return new ResponseEntity<>(result, HttpStatus.OK);
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
	 *  @param      : void
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
	 *  @created    : 2024-01-31
	 *  @param      :
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 문의 관리 - 회원 문의 목록(날짜 필터링)
	 * */
	@GetMapping("/getMemberInquiryListByDate")
	public ResponseEntity<?> getMemberInquiryListByDate(
	    @RequestParam String startDate,
	    @RequestParam String endDate
	) {
		Map<String, String> date = new HashMap<>();
		date.put("startDate", startDate);
		date.put("endDate", endDate);

	    List<Inquiry> list = adminService.getMemberInquiryListByDate(date);
	    System.out.println("받아온 리스트 : "+list);
	    
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
		List<Inquiry> list = adminService.getDoctorInquiryList();
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
