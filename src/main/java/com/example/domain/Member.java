package com.example.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;

	// 회원 이메일
	@Column(name = "member_email")
	private String memberEmail;

	// 비밀번호
	@Column(name = "member_pwd")
	private String memberPwd;

	// 회원이름
	@Column(name = "member_name")
	private String memberName;

	// 회원생년월일
	@Column(name = "member_birth")
	private String memberBirth;

	// 회원성별
	@Column(name = "member_sex")
	private String memberSex;

	// 회원연락처
	@Column(name = "member_tel")
	private String memberTel;

	// 회원메인주소
	@Column(name = "member_addr_main")
	private String memberAddrMain;

	// 회원상세주소
	@Column(name = "member_addr_detail")
	private String memberAddrDetail;

	// 회원가입일
	@Column(name = "member_join_date")
	@CreationTimestamp
	private LocalDateTime memberJoinDate;

	// 회원탈퇴일
	@Column(name = "member_leave_date")
	private LocalDateTime memberLeaveDate;

	// 회원신용카드
	@Column(name = "member_credit_num")
	private String memberCreditNum;

	// 회원포인트
	@Column(name = "member_point")
	private Integer memberPoint;

	// 회원권한
	@Column(name = "member_auth")
	private String memberAuth;

	@ManyToMany
	@JoinTable(name = "member_role", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public String toString() {
		return "User{" +
                "memberId = " + memberId +
                ", memberEmail = '" + memberEmail + '\'' +
                ", memberName = '" + memberName + '\'' +
                ", memberPwd = '" + memberPwd + '\'' +
                ", memberJoinDate = " + memberJoinDate +
                '}';
	}

	public void addRole(Role role) {
		roles.add(role);
	}

}
