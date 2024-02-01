package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Doctor;
import com.example.domain.DoctorAvail;
import com.example.domain.Inquiry;
import com.example.domain.MemberHistory;
import com.example.service.DoctorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

	private final DoctorService doctorService;

	/**
	 * @author : 박병태
	 * @created : 2024-01-22
	 * @param : Integer doctorId(의사 id번호)
	 * @return : List(Generic)
	 * @explain : 해당 의사의 진료 불러오기
	 */
	@GetMapping("/getDoctorCurrentHistory")
	public ResponseEntity<?> getDoctorCurrentHistory(@RequestParam Integer doctorId) {
		List<?> list = doctorService.getDoctorCurrentHistory(doctorId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @author : 박병태
	 * @created : 2024-01-23
	 * @param : Integer reservationNum(진료id)
	 * @return : MemberHistory
	 * @explain : 특정 진료 상세보기 조회
	 */
	@GetMapping("/getDetailedHistory")
	public ResponseEntity<?> getDetailedHistory(@RequestParam Integer certificateNum) {
		MemberHistory item = doctorService.getDetailedHistory(certificateNum);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-01
     *  @param		: Integer doctorId
     *  @return		: List<Inquiry> list
     * 	@explain	: 의사) 문의내역 조회
     * */
	@GetMapping("/getDoctorInquiryList")
	public ResponseEntity<?> getDoctorInquiry(@RequestParam Integer doctorId) {
		List<Inquiry> list = doctorService.getDoctorInquiryList(doctorId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/*
	 * 작업 중 - 박병태
	 * 
	 * @PostMapping("/postDoctorInquiry") public ResponseEntity<?>
	 * postDoctorInquiry(@RequestBody Inquiry inquiry){ String response = null;
	 * if(adminService.postInquiry(inquiry) > 0) { response = "결제완료 등록 성공"; } else {
	 * response = "결제완료 등록 실패"; } return new ResponseEntity<>(response,
	 * HttpStatus.OK); } adf
	 */

	/**
	 * @author : 이성규
	 * @created : 2024-01-26
	 * @param : String doctorMajor(의사전공)
	 * @return : List(Generic)
	 * @explain : 진료) 의사목록조회
	 */
	@GetMapping("/getDoctorClinicList")
	public ResponseEntity<?> getDoctorList(@RequestParam String doctorSubject) {
		List<?> list = doctorService.getDoctorClinicList(doctorSubject);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @author : 이성규
	 * @created : 2024-01-27
	 * @param : Integer doctorId (의사 id번호)
	 * @return : List(Generic)
	 * @explain : 진료) 의사상세 - 리뷰
	 */
	@GetMapping("/getDoctorReview")
	public ResponseEntity<?> getDoctorReview(@RequestParam Integer doctorId) {
		List<?> list = doctorService.getDoctorReview(doctorId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @author : 백두산
	 * @created : 2024-01-29
	 * @param : Integer doctorId
	 * @return : List<?> list
	 * @explain : 의사 비대면진료 목록 조회 (접수대기/진료목록/진료종료)
	 */
	@GetMapping("/getDoctorNonFaceToFaceList")
	public ResponseEntity<?> getDoctorNonFaceToFaceList(@RequestParam Integer doctorId) {
		Map<String, List<?>> list = doctorService.getDoctorNonFaceToFaceList(doctorId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @author : 정하림
	 * @created : 2024-01-29
	 * @param : String doctorEmail
	 * @return : HashMap
	 * @explain : 의사정보 조회(의사정보+진료시간)
	 */
	@GetMapping("/getDoctorEntireInfoList")
	public ResponseEntity<?> getDoctorEntireInfoList(@RequestParam String doctorEmail) {
		Doctor doctor = doctorService.getDoctorInfoList(doctorEmail);

		List<DoctorAvail> availlist = doctorService.getDoctorAvailList(doctorEmail);

		Map<String, Object> result = new HashMap<>();
		result.put("doctorInfo", doctor);
		result.put("doctorAvailability", availlist);

		return ResponseEntity.ok(result);
	}

	/**
	 * @author : 정하림
	 * @created : 2024-01-30
	 * @param : void
	 * @return : ResponseEntity
	 * @explain : 의사 대시보드 목록 조회
	 */
//	@GetMapping("/getDoctorDashBoard")
//	public ResponseEntity<?> getDoctorDashBoard(@RequestParam String doctorEmail) {
//		//대시보드 값들 한번에 가져와서 찍어주기
//	}
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-31
     *  @param		: Integer memberId
     *  @return		: List<MemberHistory>
     * 	@explain	: 환자 진료내역 조회
     * */	
	@GetMapping("/getPatientDetail")
	public ResponseEntity<?> getPatientDetail(@RequestParam Integer memberId){
		Map<String,List<?>> list = doctorService.getPatientDetail(memberId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
