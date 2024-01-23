package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.Payment;
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
    	List<?> list = paymentService.getUserPaymentInfo(memberId);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer paymentId(결재 건 id번호)
     *  @return		: Payment (결제 건 정보)
     *  @explain	: 특정 건 결제정보 조회
     * */
	@GetMapping("/getUserPaymentInfoById")
	public ResponseEntity<?> getUserPaymentInfoById(@RequestParam Integer paymentId){
		System.out.println("getUserPaymentInfoById paymentId: "+ paymentId);
    	Payment item = paymentService.getUserPaymentInfoById(paymentId);
    	System.out.println(item.toString());
    	return new ResponseEntity<>(item, HttpStatus.OK);
    }
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Payment (Entity)
     *  @return		: String (결과)
     *  @explain	: 결제 건 정보 DB에 저장 (결재 전 요청)
     * */
	@PostMapping("/recordTransaction")
	public ResponseEntity<?> recordTransaction(@RequestBody Payment transactionRequestData) {
		String response;
		if(paymentService.recordTransaction(transactionRequestData) > 0) {
			response = "결제 건 등록 성공";
		} else {
			response = "결제 건 등록 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	};
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer paymentId (결제id), String transactionType (결제방식)
     *  @return		: String (결과)
     *  @explain	: 결제정보 DB에 저장 (결재전 요청)
     * */
	@PutMapping("/completePayment")
	public ResponseEntity<?> completePayment(@RequestParam Integer paymentId, @RequestParam String transactionType){
		System.out.println("payment컨트롤러: "+paymentId+transactionType);
		String response;
		if(paymentService.completePayment(paymentId, transactionType) > 0) {
			response = "결제완료 등록 성공";
		} else {
			response = "결제완료 등록 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

