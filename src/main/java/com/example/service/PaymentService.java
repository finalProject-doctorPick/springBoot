package com.example.service;

import java.util.List;

import com.example.domain.Member;
import com.example.domain.Payment;

public interface PaymentService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 사용자 결제정보 조회
	List<?> getUserPaymentInfo(Integer memberId);
	
	// 특정 건 결제정보 조회
	Payment getUserPaymentInfoById(Integer paymentId);

	//결제정보 DB에 저장 (결재전 요청)
	Integer recordTransaction(Payment paymentCompleteData);

	//결제완료 DB에 등록
	Integer completePayment(Integer paymentId, String transactionType);

}
