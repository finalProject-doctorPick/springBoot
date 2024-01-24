package com.example.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.domain.DashBoard;
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

	// 관리자) 대시보드 - 월 매출 조회
	List<DashBoard> getMonthlySales();

	// 포인트 결재시 포인트 변경
	Integer deductPoint(HashMap<String, Object> map);
	
	//카드번호+포인트 잔액 불러오기
	Member getUserPaymentMethodAmount(Integer memberId);

	// 결재건의 연결된 회원id번호 조회
	Integer getMemberId(Integer paymentId);

	// 회원 보유 포인트 조회
	Integer getMemberPoint(Integer memberId);



}
