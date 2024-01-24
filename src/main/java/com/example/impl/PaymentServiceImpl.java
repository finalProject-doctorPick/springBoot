package com.example.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.PaymentDAO;
import com.example.domain.Payment;
import com.example.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	
	private final PaymentDAO paymentDAO;
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer memberId(회원 id번호)
     *  @return		: List<?> (결제정보)
     *	@explain	: 유저 결제정보 조회
     * */

	@Override
	public List<?> getUserPaymentInfo(Integer memberId) {
		List<Payment> list = paymentDAO.getUserPaymentInfo(memberId);
        return list;
	}

	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer paymentId(결재 건 id번호)
     *  @return		: Payment (결제 건 정보)
     *  @explain	: 특정 건 결제정보 조회
     * */
	@Override
	public Payment getUserPaymentInfoById(Integer paymentId) {
		Payment item = paymentDAO.getUserPaymentInfoById(paymentId);
		return item;
	}
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Payment (Entity)
     *  @return		: Integer
     *  @explain	: 결제 건 정보 DB에 저장 (결재전 요청)
     * */
	@Override
	public Integer recordTransaction(Payment paymentCompleteData) {
		return paymentDAO.recordTransaction(paymentCompleteData); 
	}


	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer paymentId (결제id), String transactionType (결제방식)
     *  @return		: String (결과)
     *  @explain	: 결제정보 DB에 저장 (결재전 요청)
     * */
	@Override
	public Integer completePayment(Integer paymentId, String transactionType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("transactionType", transactionType);
		map.put("paymentId", paymentId);
		return paymentDAO.completePayment(map);
	}


}
