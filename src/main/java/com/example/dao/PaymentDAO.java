package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;
import com.example.domain.Payment;

@Mapper
public interface PaymentDAO {
	// 회원 결제정보 조회
	List<Payment> getUserPaymentInfo(Integer memberId);

	// 특정 건 결제정보 조회
	Payment getUserPaymentInfoById(Integer paymentId);
	
	//결제 건 정보 DB에 저장 (결재전 요청)
	Integer recordTransaction(Payment paymentCompleteData);

	//결제완료 DB에 등록
	Integer completePayment(HashMap<String, Object> map);

	//카드번호+포인트 잔액 불러오기
	Member getUserPaymentMethodAmount(Integer memberId);


}
