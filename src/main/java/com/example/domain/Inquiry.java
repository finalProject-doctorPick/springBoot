package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Inquiry {
	private Integer inquiryId;
	private String 	inquiryType;
	private String 	inquiryTitle;
	private String 	inquiryWriterEmail;
	private String	inquiryComments;
	private String 	inquiryRegdate;
	private String 	inquiryAnswer;
	private String 	inquiryAnswerRegdate;
	private String 	status;
	private String 	originFileName;
	private String 	fileExtension;
	private String 	fileName;
	private String 	filePath;
	
	/*********** 의사 *************/
	// 의사 ID
	private Integer doctorId;
	
	// 의사 이메일
	private String doctorEmail;
	
	// 소속병원 ID
	private Integer hospitalId;
	
	// 소속병원 이름
	private String hospitalName;
	
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
	
	
	/*********** 약국 *************/
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
	
	// 약국 생년월일
	private String drugstoreBirth;
	
	// 약국 성별
	private String drugstoreSex;
	
	// 약국 연락처
	private String drugstoreTel;

	
	/*********** 회원 *************/
	// 회원 ID
	private Integer memberId;

	// 회원  이메일
	private String memberEmail;

	// 비밀번호
	private String memberPwd;

	// 회원 이름
	private String memberName;

	// 회원 생년월일
	private String memberBirth;

	// 회원 성별
	private String memberSex;

	// 회원 연락처
	private String memberTel;

	// 회원 메인주소
	private String memberAddrMain;

	// 회원 상세주소
	private String memberAddrDetail;

	// 회원 나머지주소
	private String memberAddrSubdetail;
	// 회원 가입일
	private LocalDateTime memberJoinDate;

	// 회원 탈퇴일
	private LocalDateTime memberLeaveDate;

	// 회원 신용카드
	private String memberCreditNum;

	// 회원 포인트
	private Integer memberPoint;

	// 회원 권한
	private String memberAuth;


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
	
	public void setMemberTel(String memberTel) {
		if (memberTel.length() == 8) {
			this.memberTel = memberTel.substring(0, 4) + "-" + memberTel.substring(4);
		} else if (memberTel.length() == 9) {
			this.memberTel = memberTel.substring(0, 2) + "-" + memberTel.substring(2, 5) + "-" + memberTel.substring(5);
		} else if (memberTel.length() == 10) {
			this.memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6);
		} else if (memberTel.length() == 11) {
			this.memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7);
		} else {
			this.memberTel = memberTel;
		}
	}
	
	public void setDrugstoreTel(String drugstoreTel) {
		if (drugstoreTel.length() == 8) {
			this.drugstoreTel = drugstoreTel.substring(0, 4) + "-" + drugstoreTel.substring(4);
		} else if (drugstoreTel.length() == 9) {
			this.drugstoreTel = drugstoreTel.substring(0, 2) + "-" + drugstoreTel.substring(2, 5) + "-" + drugstoreTel.substring(5);
		} else if (drugstoreTel.length() == 10) {
			this.drugstoreTel = drugstoreTel.substring(0, 3) + "-" + drugstoreTel.substring(3, 6) + "-" + drugstoreTel.substring(6);
		} else if (drugstoreTel.length() == 11) {
			this.drugstoreTel = drugstoreTel.substring(0, 3) + "-" + drugstoreTel.substring(3, 7) + "-" + drugstoreTel.substring(7);
		} else {
			this.drugstoreTel = drugstoreTel;
		}
	}
}

