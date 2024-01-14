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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drugstore")
@NoArgsConstructor
@Setter
@Getter
public class DrugstoreDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// 회원ID
	@Id
	@Column(name = "drugstore_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drugstoreId;
	
	@Column(name = "drugstore_email")
	private String drugstoreEmail;
	
	@Column(name = "drugstore_name")
	private String drugstoreName;
	
	@Column(name = "drugstore_addr_main")
	private String drugstoreAddrMain;
	
	@Column(name = "drugstore_addr_detail")
	private String drugstoreAddrDetail;
	
	@Column(name = "drugstore_lati")
	private String drugstoreLati;
	
	@Column(name = "drugstore_long")
	private String drugstoreLong;
	
	@Column(name = "drugstore_join_date")
	private String drugstoreJoinDate;
	
	@Column(name = "drugstore_leave_date")
	private String drugstoreLeaveDate;

	@Column(name = "drugstore_confirm_yn")
	private String drugstoreConfirmYn;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "drugstore_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleDTO> roles = new HashSet<>();

	public void addRole(RoleDTO role) {
		roles.add(role);
	}
}
