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
	public Payment getUserPaymentInfoById(Integer paymentId);

	//결제 건 정보 DB에 저장 (결재전 요청)
	public Integer recordTransaction(Payment paymentCompleteData);

	//결제완료 DB에 등록
	public Integer completePayment(Integer paymentId, String transactionType);

	// 카드번호+포인트 잔액 불러오기
	public Member getUserPaymentMethodAmount(Integer memberId);

	// 카드번호 등록
	public Integer recordBillingKey(Member entry);

	// 등록된 카드 삭제
	public Integer deleteRegisteredCard(Integer memberId);

	// 포인트 충전 내역
	public Integer chargePoint(PointHistory entry);

	// 포인트 결제
	public Integer payPoints(Payment entry);

	public List<DashBoard> getMonthlySales();

	//진료번호에 해당하는 결제 방식 가져오기
	String getPaymentMethod(Integer certificateNum);
	
}
