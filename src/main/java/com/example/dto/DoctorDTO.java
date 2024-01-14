package com.example.dto;

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

import com.example.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@Setter
@Getter
public class DoctorDTO implements Serializable{
	
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

	@Column(name = "doctor_join_date")
	private String doctorJoinDate;

	@Column(name = "doctor_confirm_yn")
	private String doctorConfirmYn;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "doctor_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleDTO> roles = new HashSet<>();
	
	public void addRole(RoleDTO doctorRole) {
		roles.add(doctorRole);
	}
}
