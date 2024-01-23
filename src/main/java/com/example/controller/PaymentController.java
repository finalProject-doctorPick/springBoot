package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Hospital;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.service.DoctorService;
import com.example.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer memberId(회원 id번호)
     *  @return		: List<?> (결제정보)
     *  @explain	: 유저 결제정보 조회
     * */
	@GetMapping("/getUserPaymentInfo")
	public ResponseEntity<?> getUserPaymentInfo(@RequestParam Integer memberId){
		System.out.println("getUserPaymentInfo memberId: "+ memberId);
    	List<?> list = paymentService.getUserPaymentInfo(memberId);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
	

}
