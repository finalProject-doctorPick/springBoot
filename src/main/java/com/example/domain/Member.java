package com.example.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@NoArgsConstructor
@Setter
@Getter
public class Member {

	// 회원ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;

	// 회원 이메일
	private String memberEmail;

	// 비밀번호
	private String memberPwd;

	// 회원이름
	private String memberName;

	// 회원생년월일
	private String memberBirth;

	// 회원성별
	private String memberSex;

	// 회원연락처
	private String memberTel;

	// 회원메인주소
	private String memberAddrMain;

	// 회원상세주소
	private String memberAddrDetail;

	// 회원가입일
	@CreationTimestamp
	private LocalDateTime memberJoinDate;

	// 회원탈퇴일
	private LocalDateTime memberLeaveDate;

	// 회원신용카드
	private String memberCreditNum;

	// 회원포인트
	private Integer memberPoint;

	// 회원권한
	private String memberAuth;

	@ManyToMany
	@JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberEmail=" + memberEmail + ", memberPwd=" + memberPwd
				+ ", memberName=" + memberName + ", memberBirth=" + memberBirth + ", memberSex=" + memberSex
				+ ", memberTel=" + memberTel + ", memberAddrMain=" + memberAddrMain + ", memberAddrDetail="
				+ memberAddrDetail + ", memberJoinDate=" + memberJoinDate + ", memberLeaveDate=" + memberLeaveDate
				+ ", memberCreditNum=" + memberCreditNum + ", memberPoint=" + memberPoint + ", memberAuth=" + memberAuth
				+ ", roles=" + roles + "]";
	}
	
	

	public void addRole(Role role) {
		roles.add(role);
	}

}
