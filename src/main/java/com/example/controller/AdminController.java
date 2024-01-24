package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Doctor;
import com.example.service.AdminService;
import com.example.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	private final DoctorService doctorService;
	
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-19
     *  @param		: String searchKeyword
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 회원 목록 조회
     * */
    @GetMapping("/getMemberList")
    public ResponseEntity<?> getMemberList(@RequestParam String searchKeyword){
    	List<?> list = adminService.getMemberList(searchKeyword);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-22
     *  @param		: void
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 문의 목록 조회
     * */
    @GetMapping("/getInquiryList")
    public ResponseEntity<?> getInquiryList(){
    	List<?> list = adminService.getInquiryList("");
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      :
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 의사 전체 목록 조회 
	 * */
	@GetMapping("/getDoctorsList")
	public ResponseEntity<?> getDoctorsList(){
		List<Doctor> list = doctorService.getDoctorsList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}


	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      :
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 대시보드 - 의사 등록 요청 수
	 * */
	@GetMapping("/getDoctorRequestCnt")
	public ResponseEntity<Integer> getDoctorRequestCnt() {
		int requestCnt = doctorService.getDoctorRequestCnt();
		return new ResponseEntity<>(requestCnt, HttpStatus.OK);
	}

	
	/**
	 * 	@author     : 정하림
	 *  @created    : 2024-01-23
	 *  @param      :
	 *  @return     : ResponseEntity
	 * 	@explain    : 관리자) 의사 관리 - 등록 요청 의사 목록
	 * */
	@GetMapping("/getRegistRequestList")
	public ResponseEntity<?> getRegistRequestList() {
		List<Doctor> list = doctorService.getRegistRequestList();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
