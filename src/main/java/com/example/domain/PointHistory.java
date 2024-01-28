package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointHistory {
	
	//포인트 충전/결제 내역id
	private Integer pointHistoryId;
	
	//내역 회원id번호
	private Integer memberId;
	
	//결재 타입(카드/현금)
	private String transactionType;
	
	//금액
	private Integer amount;
	
	//내역 
	private String transactionDate;
}