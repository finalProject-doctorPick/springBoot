package com.example.service;

import java.util.List;

import com.example.domain.Member;

public interface PaymentService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 사용자 결제정보 조회
	List<?> getUserPaymentInfo(Integer memberId);


}
