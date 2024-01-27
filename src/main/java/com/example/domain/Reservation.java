package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	//에약 id번호
	private Integer reservationNum;
	
	//에약회원id번호
	private Integer memberId;
	
	//예약회원id번호
	private Integer doctorId;
	
	//예약 진료일
	private String reservationDate;
	
	//예약 상태
	private String reservationStatus;
	
	//결제방법
	private String reservationPayment;
	
	//업로드파일
	private String fileKey;
	
	// 환자증상
	private String patientComments;

}
