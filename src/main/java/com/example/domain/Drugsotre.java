package com.example.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Drugsotre {
	// 약국 ID 
	private Integer drugstoreId;
	
	// 약국 이메일
	private String drugstoreEmail;
	
	// 약국명
	private String drugstoreName;
	
	// 약국 메인주소
	private String drugstoreAddrMain;
	
	// 약국 상세주소
	private String drugstoreAddrDetail;
	
	// 약국 위도
	private BigDecimal drugstoreLati;
	
	// 약국 경도
	private BigDecimal drugstoreLong;
	
	// 약국 가입일
	private LocalDateTime joinDate;
	
	// 약국 탈퇴일
	private LocalDateTime leaveDate;
}
