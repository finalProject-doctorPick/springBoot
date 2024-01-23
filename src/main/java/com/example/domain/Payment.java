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
	
	// 회원ID번호
	private Integer memberId;
	
	// 결제일
	private String paymentDate;
	
	// 결제금액
	private Integer Amount;
	
	// 결제방식
	private String TransactionType;
	
	// 결제상태(Y:결제완료/N:결제안함)
	private String PaymentStatus;
	
	// 처방ID
	private String CertificateNum;
	
}
