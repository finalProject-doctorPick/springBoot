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
	
	// 의사 주소(부상세)
	private String hospitalAddrSubdetail;

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
	
	public void setHospitalTel(String hospitalTel) {
        if (hospitalTel.length() == 8) {
            this.hospitalTel = hospitalTel.substring(0, 4) + "-" + hospitalTel.substring(4);
        } else if (hospitalTel.length() == 9) {
            this.hospitalTel = hospitalTel.substring(0, 2) + "-" + hospitalTel.substring(2, 5) + "-" + hospitalTel.substring(5);
        } else if (hospitalTel.length() == 10) {
            this.hospitalTel = hospitalTel.substring(0, 3) + "-" + hospitalTel.substring(3, 6) + "-" + hospitalTel.substring(6);
        } else if (hospitalTel.length() == 11) {
            this.hospitalTel = hospitalTel.substring(0, 3) + "-" + hospitalTel.substring(3, 7) + "-" + hospitalTel.substring(7);
        } else {
            this.hospitalTel = hospitalTel;
        }
    }
	
}
