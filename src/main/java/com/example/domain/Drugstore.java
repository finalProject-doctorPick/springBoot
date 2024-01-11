package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Drugstore {
	// 약국 ID
	@Id
	private Integer drugstoreId;
	
	// 약국 이메일
	private String drugstoreEmail;
	
	// 약국 이름
	private String drugstoreName;
	
	// 약국 주소(주)
	private String drugstoreAddrMain;
	
	// 약국 주소(부)
	private String drugstoreAddrDetail;
	
	// 약국 위도
	private String drugstoreLati;
	
	// 약국 경도
	private String drugstoreLong;
	
	// 약국 가입일
	private String drugstoreJoinDate;
	
	// 약국 탈퇴일
	private String drugstoreLeaveDate;
}
