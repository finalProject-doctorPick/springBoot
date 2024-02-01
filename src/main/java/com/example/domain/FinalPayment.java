package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinalPayment {
	
	// 결제ID
	private Integer paymentId;
	
	// 결재방식
	private String reservationPayment;

		
}
