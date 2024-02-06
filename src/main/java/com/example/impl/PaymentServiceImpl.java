package com.example.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.PaymentDAO;
import com.example.domain.DashBoard;
import com.example.domain.Member;
import com.example.domain.Payment;
import com.example.domain.PointHistory;
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
	@Transactional
	public Integer completePayment(Integer paymentId, String transactionType) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("transactionType", transactionType);
		map.put("paymentId", paymentId);
		return paymentDAO.completePayment(map);
	}

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-24
	 *  @param		: memberid(회원id)
	 *  @return		: Member (카드번호+현재 포인트 잔액)
	 *  @explain	: 카드번호+포인트 잔액 불러오기
	 * */
	@Override
	public Member getUserPaymentMethodAmount(Integer memberId) {
		Member item = paymentDAO.getUserPaymentMethodAmount(memberId);
		return item;
	}

	/**
	 * 	@author 	: 정하림
	 *  @created	: 2024-01-24
	 *  @param		: void
	 *  @return		: List<DashBoard>
	 *  @explain	: 관리자) 대시보드 - 월 매출 조회
	 * */
	@Override
	public List<DashBoard> getMonthlySales() {
		return paymentDAO.getMonthlySales();
	}

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-25
	 *  @param		: String billingKey, String customerKey, Integer memberId(회원id)
	 *  @return		: Integer (결과)
	 *  @explain	: 카드등록 시 BillingKey 및 customerKey 등록
	 * */
	@Override
	public Integer recordBillingKey(Member entry) {
		return paymentDAO.recordBillingKey(entry);
	}


	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-26
	 *  @param		: Integer memberId(회원id)
	 *  @return		: Integer (결과)
	 *  @explain	: 등록된 카드 삭제
	 * */
	@Override
	public Integer deleteRegisteredCard(Integer memberId) {
		return paymentDAO.deleteRegisteredCard(memberId);
	}


	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-26
	 *  @param		: Integer memberId(회원id)
	 *  @return		: Integer (결과)
	 *  @explain	: 카드 충전 및 충전내역 등록
	 * */
	@Override
	@Transactional
	public Integer chargePoint(PointHistory entry) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", entry.getMemberId());
		map.put("amount", entry.getAmount());
		if(paymentDAO.updatePoint(map) > 0){
			return paymentDAO.recordPointEntry(entry);
		} else {
			return -1;
		}

	}

	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-28
	 *  @param		: Payment
	 *  @return		: Integer(결과)
	 *  @explain	: 카드 결제
	 * */
	@Override
	@Transactional
	public Integer payPoints(Payment entry) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", entry.getMemberId());
		map.put("amount", entry.getAmount());
		if(paymentDAO.updatePoint(map) > 0){
		    PointHistory pointHistory = new PointHistory();
		    pointHistory.setMemberId(entry.getMemberId());
		    pointHistory.setTransactionType(entry.getTransactionType());
		    pointHistory.setAmount(entry.getAmount());    
			if(paymentDAO.recordPointEntry(pointHistory)>0) {
				map.put("transactionType", entry.getTransactionType());
				map.put("paymentId", entry.getPaymentId());
				return paymentDAO.completePayment(map);
			}else{
				return -1;
			}
		} else {
			return -1;
		}
	}

	
	/**
	 * 	@author 	: 박병태
	 *  @created	: 2024-01-28
	 *  @param		: certificateNum(진료id번호)
	 *  @return		: String reservationPayment(결제방식)
	 *  @explain	: 진료비 결제방식 조회
	 * */
	@Override
	public Payment getPaymentMethod(Integer certificateNum) {
		return paymentDAO.getPaymentMethod(certificateNum);
	}
	
}
