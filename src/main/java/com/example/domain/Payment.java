package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	// 결제ID
	private Integer paymentId;
	
	// 의사ID
	private Integer doctorId;
	
	// 의사 이름
	private String doctorName;
	
	// 병원 이름
	private String hospitalName;
	
	// 회원ID번호
	private Integer memberId;
	
	// 결제일
	private String paymentDate;
	
	// 결제금액
	private Integer amount;
	
	// 결제방식
	private String reservationPayment;
	
	// 결제상태(Y:결제완료/N:결제안함)
	private String paymentStatus;
	
	// 처방ID
	private Integer certificateNum;
	
	// 결제정보생성일
	private String paymentRegdate;
		
}
