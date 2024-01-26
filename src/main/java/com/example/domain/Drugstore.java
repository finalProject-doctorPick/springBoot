package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Drugstore {
	
	// 약국 ID
	private Integer drugstoreId;
	
	// 약국 이메일
	private String drugstoreEmail;
	
	// 약국 비밀번호
	private String drugstorePwd;
	
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
	
	// 약국 확인 여부
	private String drugstoreConfirmYn;
	
	// 파일 키
	private String fileKey;
	
	// 약국 생년월일
	private String drugstoreBirth;
	
	// 약국 성별
	private String drugstoreSex;
	
	// 약국 연락처
	private String drugstoreTel;
	
	// 약국 
	
	// 약국 역할
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role drugstoreRole) {
		roles.add(drugstoreRole);
	}
}
