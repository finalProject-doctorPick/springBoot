package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.MemberHistory;
import com.example.service.AdminService;
import com.example.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

	private final DoctorService doctorService;

	private final AdminService adminService;
	

	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-22
     *  @param		: Integer doctorId(의사 id번호)
     *  @return		: List(Generic)
     * 	@explain	: 해당 의사의 진료 불러오기
     * */
	@GetMapping("/getDoctorCurrentHistory")
	public ResponseEntity<?> getDoctorCurrentHistory(@RequestParam Integer doctorId){

		System.out.println(doctorId);
		List<?> list = doctorService.getDoctorCurrentHistory(doctorId);
		System.out.println("getDoctorCurrentHistory: "+list.toString());
		return new ResponseEntity<>(list, HttpStatus.OK);

    }

	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer reservationNum(진료id)
     *  @return		: MemberHistory 
     * 	@explain	: 특정 진료 상세보기 조회
     * */
	@GetMapping("/getDetailedHistory")
	public ResponseEntity<?> getDetailedHistory(@RequestParam Integer certificateNum){
		MemberHistory item = doctorService.getDetailedHistory(certificateNum);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer doctorId(의사 id번호)
     *  @return		: List(Generic)
     * 	@explain	: 의사) 문의 목록 조회
     * */
	@GetMapping("/getDoctorInquiry")
	public ResponseEntity<?> getDoctorInquiry(@RequestParam Integer doctorId ){
		String doctorEmail = doctorService.getDoctorEmailFromId(doctorId);
		List<?> list = adminService.getInquiryList(doctorEmail);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/* 작업 중 - 박병태
	@PostMapping("/postDoctorInquiry")
	public ResponseEntity<?> postDoctorInquiry(@RequestBody Inquiry inquiry){
		String response = null;
		if(adminService.postInquiry(inquiry) > 0) {
			response = "결제완료 등록 성공";
		} else {
			response = "결제완료 등록 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	adf
	*/
	

}
