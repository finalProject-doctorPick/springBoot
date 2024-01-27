package com.example.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

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
	
	// 카드등록 시 필요한 키(토스제공)
	private String billingKey;
	
	// 카드등록 시 필요한 키(본 사이트 제공)
	private String customerKey;
	
	private Set<Role> roles = new HashSet<>();
	
	public void addRole(Role memberRole) {
		roles.add(memberRole);
	}
}
