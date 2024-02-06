package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.DashBoard;
import com.example.domain.Inquiry;
import com.example.domain.Member;
import com.example.domain.Reservation;
import com.example.service.InquiryService;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
	private final InquiryService inquiryService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-18
     *  @param		: Users userData
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 최근 진료 내역
     * */
    @GetMapping("/currentHistory")
    public ResponseEntity<?> getMemberCurrentHistory(@RequestParam Integer memberId){
    	List<?> list = memberService.getMemberCurrentHistory(memberId);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 정하림
     *  @created	: 2024-01-22
     *  @param		: 
     *  @return		: ResponseEntity
     * 	@explain	: 나이대별 회원 조회
     * 
     * */
    @GetMapping("/getMembersCntByAge")
    public ResponseEntity<?> getMembersCntByAge(){
    	List<DashBoard> list = memberService.getMembersCntByAge();
		return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 이성규	 
     *  @created	: 2024-01-25
     *  @param		: Users userData
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 리뷰 조회
     * */
    @GetMapping("/getMemberReview")
    public ResponseEntity<?> getMemberReview(@RequestParam Integer memberId){
    	List<?> list = memberService.getMemberReview(memberId);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-26
     *  @param		: String memberEmail
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 정보 조회
     * */
    @GetMapping("/getMemberInfo")
    public ResponseEntity<?> getMemberInfo(@RequestParam String memberEmail){
    	Member m = memberService.findMemberByEmail(memberEmail);
		return new ResponseEntity<>(m, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-27
     *  @param		: Member updateMemberData
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 정보 수정
     * */
    @PostMapping("/updateMemberInfo")
    public ResponseEntity<?> updateMemberInfo(@RequestBody Member updateMemberData) {
    	ResponseEntity<?> response = memberService.updateMemberInfo(updateMemberData);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /** 
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-27
     *  @param		: Integer memberId
     *  @return		: ResponseEntity
     * 	@explain	: 회원) 문의목록 조회
     * */
    @GetMapping("/getMemberInquiryList")
    public ResponseEntity<?> getMemberInquiry(@RequestParam Integer memberId){
    	List<Inquiry> response = inquiryService.getMemberInquiryList(memberId);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /**
     * 	@author 	: 이성규	 
     *  @created	: 2024-01-27
     *  @param		: void
     *  @return		: ResponseEntity
     * 	@explain	: 진료신청 (reservation)
     * */
    @PostMapping("/registReservation")
    public ResponseEntity<?> registReservation(@ModelAttribute Reservation reservationData, @RequestPart(name = "fileList", required = false) List<MultipartFile> fileList)  {
        memberService.registReservation(reservationData, fileList);
        return new ResponseEntity<>("성공적으로 접수되었습니다", HttpStatus.OK);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-30
     *  @param		: List<Integer> reviewId
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 리뷰 삭제
     * */
    @DeleteMapping("/deleteReviewId")
    public ResponseEntity<?> deleteReviewId(@RequestBody List<Integer> reviewList){
    	ResponseEntity<?> response = memberService.deleteReviewId(reviewList);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /** 
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-02
     *  @param		: Integer certificateNum
     *  @return		: ResponseEntity
     * 	@explain	: 회원) 진료실 입장
     * */    
    @GetMapping("/updateCertificateStaus")
    public ResponseEntity<?> updateCertificateStaus(@RequestParam Integer certificateNum){
    	ResponseEntity<?> response = memberService.updateCertificateStaus(certificateNum);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    /** 
     * 	@author 	: 이성규	 
     *  @created	: 2024-02-02
     *  @param		: Integer certificateNum
     *  @return		: ResponseEntity
     * 	@explain	: 회원) 진료실 대기실 입장
     * */   
    @GetMapping("/getCertificateInfo")
    public ResponseEntity<?> getCertificateInfo(@RequestParam Integer certificateNum){
    	ResponseEntity<?> response = memberService.getCertificateInfo(certificateNum);
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}