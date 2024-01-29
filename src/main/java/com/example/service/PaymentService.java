package com.example.service;

import java.util.List;
import com.example.domain.DashBoard;
import com.example.domain.Member;
import com.example.domain.Payment;
import com.example.domain.PointHistory;

public interface PaymentService {

	/**
	 *	****************MyBatis********************* 
	 * */
	// 사용자 결제정보 조회
	List<?> getUserPaymentInfo(Integer memberId);

	// 특정 건 결제정보 조회
	Payment getUserPaymentInfoById(Integer paymentId);

	//결제 건 정보 DB에 저장 (결재전 요청)
	Integer recordTransaction(Payment paymentCompleteData);

	//결제완료 DB에 등록
	Integer completePayment(Integer paymentId, String transactionType);

	// 관리자 - 월 매출 조회
	List<DashBoard> getMonthlySales();

	// 카드번호+포인트 잔액 불러오기
	Member getUserPaymentMethodAmount(Integer memberId);

	// 카드번호 등록
	Integer recordBillingKey(Member entry);

	// 등록된 카드 삭제
	Integer deleteRegisteredCard(Integer memberId);

	// 포인트 충전 내역
	Integer chargePoint(PointHistory entry);

	// 포인트 결제
	Integer payPoints(Payment entry);
	
}
