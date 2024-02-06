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

import com.example.domain.Member;
import com.example.domain.Payment;
import com.example.domain.PointHistory;
import com.example.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

	private final PaymentService paymentService;

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-24
	 *  @param		: memberid(회원id)
	 *  @return		: Member
	 *  @explain	: 회원의 결제 정보 조회
	 * */
	@GetMapping("/getUserPaymentMethodAmount")
	public ResponseEntity<?> getUserPaymentMethodAmount(@RequestParam Integer memberId){
		Member item = paymentService.getUserPaymentMethodAmount(memberId);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}


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
		Payment item = paymentService.getUserPaymentInfoById(paymentId);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-23
	 *  @param		: Payment 
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
	 *  @param		: Integer paymentId (결제id), String transactionType (결제방식), Integer paymentAmount(결제금액)
	 *  @return		: String (결과)
	 *  @explain	: 결제정보 DB에 저장 (결재전 요청)
	 * */
	@PutMapping("/completePayment")
	public ResponseEntity<?> completePayment(@RequestParam Integer paymentId, @RequestParam Integer certificateNum, @RequestParam String reservationPayment){
		
		Payment p = new Payment();
		
		p.setPaymentId(paymentId);
		p.setCertificateNum(certificateNum);
		p.setReservationPayment(reservationPayment);
		
		String response;
		if(paymentService.completePayment(p) > 0) {
			response = "결제 성공";
		} else {
			response = "결제 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	};

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-25
	 *  @param		: String billingKey, String customerKey, Integer memberId(회원id), String memberCreditNum(가려진 회원 카드), String issuerCode(카드발급사 코드)
	 *  @return		: String (결과)
	 *  @explain	: 카드등록 시 BillingKey 및 customerKey 등록
	 * */
	@PutMapping("/recordBillingKey")
	public ResponseEntity<?> recordBillingKey(@RequestBody Member entry){
		String response = null;
		if(paymentService.recordBillingKey(entry) > 0) {
			response = "카드등록 성공";
		} else {
			response = "카드등록 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	};

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-26
	 *  @param		: Integer memberId(회원id)
	 *  @return		: Integer (결과)
	 *  @explain	: 등록된 카드 삭제
	 * */
	@PutMapping("/deleteRegisteredCard")
	public ResponseEntity<?> deleteRegisteredCard(@RequestParam Integer memberId){
		String response;
		if(paymentService.deleteRegisteredCard(memberId) > 0) {
			response = "결제 성공";
		} else {
			response = "결제 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-26
	 *  @param		: PointHistory
	 *  @return		: Integer (결과)
	 *  @explain	: 카드 충전 및 충전내역 등록
	 * */
	@PostMapping("/chargePoint")
	public ResponseEntity<?> chargePoint(@RequestBody PointHistory entry ){
		String response;
		System.out.println("controller: "+ entry);
		if(paymentService.chargePoint(entry) > 0) {
			response = "포인트 내역 등록 성공";
		} else {
			response = "포인트 내역 등록 실패";
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-28
	 *  @param		: Payment
	 *  @return		: Integer(결과)
	 *  @explain	: 카드 결제
	 * */
	@PutMapping("/payPoints")
	public ResponseEntity<?> payPoints(@RequestBody Payment entry){
		String response = null;
		if(paymentService.payPoints(entry) > 0) {
			response = "포인트 결제 성공";
		} else {
			response = "포인트 결제 실패";
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-28
	 *  @param		: Integer certificateNum(진료id번호)
	 *  @return		: FinalPaymetn
	 *  @explain	: 진료비 결제방식 조회
	 * */
	@GetMapping("/getPaymentMethod")
	public ResponseEntity<?> getPamentMethod(@RequestParam Integer certificateNum){
		Payment response = paymentService.getPaymentMethod(certificateNum);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

