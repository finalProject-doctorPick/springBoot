package com.example.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "doctor")
public class DoctorEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "doctor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	
	@Column(name = "doctor_email")
	private String doctorEmail;

	@Column(name = "doctorPwd")
	private String doctorPwd;

	@Column(name = "doctor_name")
	private String doctorName;

	@Column(name = "doctor_birth")
	private String doctorBirth;

	@Column(name = "doctor_sex")
	private String doctorSex;

	@Column(name = "doctor_addr_main")
	private String doctorAddrMain;

	@Column(name = "doctor_addr_detail")
	private String doctorAddrDetail;

	@Column(name = "file_key")
	private String fileKey;
	
	@Column(name = "doctor_confirm_yn")
	private String doctorConfirmYn;
	
	@Column(name = "doctor_tel")
	private String doctorTel;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "doctor_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();
	
	public void addRole(RoleEntity doctorRole) {
		roles.add(doctorRole);
	}
}
