package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Doctor {
	// 의사 ID
	@Id
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
	
	// 의사 주소(주)
	private String doctorAddrMain;
	
	// 의사 주소(부)
	private String doctorAddrDetail;
	
	// 의사 진료 과목
	private String doctorSubject;
	
	// 의사 전공
	private String doctorMajor;
	
	// 의사 소개
	private String doctorComments;
	
	// 파일 ID
	private String fileId;
	
	// 의사 가입일
	private String doctorJoinDate;
	
	// 의사 탈퇴일
	private String doctorLeaveDate;
	
	// 의사 확인 여부
	private String doctorConfirmYn;
}
