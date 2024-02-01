package com.example.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.DashBoard;
import com.example.domain.Member;
import com.example.domain.Payment;
import com.example.domain.PointHistory;

@Mapper
public interface PaymentDAO {
	// 회원 결제정보 조회
	public List<Payment> getUserPaymentInfo(Integer memberId);

	// 특정 건 결제정보 조회
	public Payment getUserPaymentInfoById(Integer paymentId);
	
	//결제 건 정보 DB에 저장 (결재전 요청)
	public Integer recordTransaction(Payment paymentCompleteData);

	//결제완료 DB에 등록
	public Integer completePayment(HashMap<String, Object> map);

	// 포인트 결재시 포인트 변경
	public Integer deductPoint(HashMap<String, Object> map);
	
	//카드번호+포인트 잔액 불러오기
	public Member getUserPaymentMethodAmount(Integer memberId);

	// 결재건의 연결된 회원id번호 조회
	public Integer getMemberId(Integer paymentId);

	// 회원 보유 포인트 조회
	public Integer getMemberPoint(Integer memberId);

	// 카드번호 등록
	public Integer recordBillingKey(HashMap<String, Object> map);

	public Integer recordBillingKey(Member entry);
	
	// 관리자) 대시보드 - 3개월 매출 조회
	public List<DashBoard> getMonthlySales();

	// 등록된 카드 삭제
	public Integer deleteRegisteredCard(Integer memberId);

	// 포인트 충전
	public Integer updatePoint(HashMap<String, Object> map);
	
	// 충전 내역 기록
	public Integer recordPointEntry(PointHistory entry);

	// 진료비 결제방식 조회
	public String getPaymentMethod(Integer certificateNum);
	
}
