package com.example.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
	
	// 의사 ID
	private Integer doctorId;
	
	// 의사 이메일
	private String doctorEmail;
	
	// 소속병원 ID
	private Integer hospitalId;
	
	// 소속병원 이름
	private String hospitalName;
	
	// 소속병원 전화번호
	private String hospitalTel;
	
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
	
	// 파일 키
	private String fileKey;
	
	// 의사 가입일
	private String doctorJoinDate;
	
	// 의사 탈퇴일
	private String doctorLeaveDate;
	
	// 의사 확인 여부
	private String doctorConfirmYn;
	
	// 의사 리뷰 아이디
	private Integer reviewId;
	
	// 리뷰 남긴 멤버 아이디
	private Integer memberId;
	
	// 의사 평점 
	private Integer rating;
	
	// 의사 평점 평균
	private float ratingAvg;
	
	// 의사 평점 총 개수
	private Integer ratingCnt;
	
	// 의사 리뷰 총 개수
	private Integer reviewCnt;
	
	private Integer count;
	
	// 의사 리뷰 제목
	private String reviewTitle;
	private String reviewTitleA;
	private String reviewTitleB;
	private String reviewTitleC;
	
	// 의사 리뷰 내용
	private String comments;
	
	// 관리자_통계) 의사 등록 요청 수 
	private Integer doctorRequestCnt;
	

	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role doctorRole) {
		roles.add(doctorRole);
	}
	
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
	
	public void setDoctorTel(String doctorTel) {
        if (doctorTel.length() == 8) {
            this.doctorTel = doctorTel.substring(0, 4) + "-" + doctorTel.substring(4);
        } else if (doctorTel.length() == 9) {
            this.doctorTel = doctorTel.substring(0, 2) + "-" + doctorTel.substring(2, 5) + "-" + doctorTel.substring(5);
        } else if (doctorTel.length() == 10) {
            this.doctorTel = doctorTel.substring(0, 3) + "-" + doctorTel.substring(3, 6) + "-" + doctorTel.substring(6);
        } else if (doctorTel.length() == 11) {
            this.doctorTel = doctorTel.substring(0, 3) + "-" + doctorTel.substring(3, 7) + "-" + doctorTel.substring(7);
        } else {
            this.doctorTel = doctorTel;
        }
    }
	
}
