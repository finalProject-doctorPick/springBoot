package com.example.domain;

import lombok.Data;

@Data
public class Hospital {
	// 병원 ID
	private String hospitalId;
	
	// 병원명
	private String hospitalName;
	
	// 병원주소(주)
	private String hospitalAddrMain;
	
	// 병원주소(부)
	private String hospitalAddrDetail;
	
	// 가입일
	private String joinDate;
	
	// 탈퇴일
	private String leaveDate;
	
	// 파일ID
	private String fileId;
	
	// 병원 위도
	private String hospitalLati;
	
	// 병원 경도
	private String hospitalLong;
	
	// 병원 제휴 상태
	private String partnershipStatus;
}
