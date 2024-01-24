package com.example.domain;

import lombok.Data;

@Data
public class DashBoard {
	/* 통계 */
	// 회원
	private String memberBirth;
	
	private int memberCnt;
	
	private String ageGroup;

	// 의사
	private Integer doctorRequestCnt;

	private String doctorConfirmYn;
}
