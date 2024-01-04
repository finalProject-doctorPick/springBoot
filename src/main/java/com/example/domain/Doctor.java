package com.example.domain;

import lombok.Data;

@Data
public class Doctor {
	// 의사 ID
	private Integer doctorId;
	
	// 의사 이메일
	private String doctorEmail;
	
	// 소속병원 ID
	private Integer hospitalId;
	
	// 의사 비밀번호
	private String doctorPwd;
	
	// 의사 이름
	private String doctorName;
	
	// 의사 생년월일
	private String doctorBirth;
	
	// 의사 성별
	private String doctorSex;
	
	// 의사 연락처
	private String doctorTel;
	
	// 의사 메인주소
	private String doctorAddrMain;
	
	// 의사 상세주소
//	private String doctor_addr_detail;
//	
//	// 의사 진료과목
//	private String doctor_subject;
//	
//	// 의사 전공
//	private String doctor_major;
//	
//	// 의사 소개
//	private String doctor_comments;
//	
//	// 파일 ID
//	private String file_id;
}
