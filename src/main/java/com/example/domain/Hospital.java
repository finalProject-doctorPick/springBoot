package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Hospital {
	
	// 병원ID
	private Integer hospitalId;

	// 병원이름
	private String hospitalName;

	// 병원주소(주)
	private String hospitalAddrMain;

	// 병원주소(부)
	private String hospitalAddrDetail;

	// 회원가입일
	private LocalDateTime hospitalJoinDate;
	
	// 회원탈퇴일
	private LocalDateTime hospitalLeaveDate;
	
	// 병원 전화번호
	private String hospitalTel;
	
	// 병원 위도
	private float hospitalLati;
	
	// 병원 경도
	private float hospitalLong;
	
	// 제휴 상태
	private String partnershipStatus;
	
	// 지역별
	private String region;
	
	// 지역별 병원 수
	private int count;
	
	private int partnershipNcount;
	
	private int partnershipYcount;
	
	
}
