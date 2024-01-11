package com.example.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Data;

@Data
public class Hospital {
	
	// 병원ID
	@Id
	@Column(name = "hospital_id")
	private Integer hospitalId;

	// 병원이름
	@Column(name = "hospital_name")
	private String hospitalName;

	// 병원주소(주)
	@Column(name = "hospital_addr_main")
	private String hospitalAddrMain;

	// 병원주소(부)
	@Column(name = "hospital_addr_detail")
	private String hospitalAddrDetail;

	// 회원가입일
	@Column(name = "join_date")
	private LocalDateTime hospitalJoinDate;
	
	// 회원탈퇴일
	@Column(name = "leave_date")
	private LocalDateTime hospitalLeaveDate;
	
	// 병원 위도
	@Column(name = "hospital_lati")
	private float hospitalLati;
	
	// 병원 경도
	@Column(name = "hospital_long")
	private float hospitalLong;
	
	// 제휴 상태
	@Column(name = "partnership_status")
	private String partnershipStatus;
	
	
}
