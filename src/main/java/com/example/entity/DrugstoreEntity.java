package com.example.entity;

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
@Table(name = "drugstore")
public class DrugstoreEntity{
	
	@Id
	@Column(name = "drugstore_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer drugstoreId;
	
	@Column(name = "drugstore_email")
	private String drugstoreEmail;
	
	@Column(name = "drugstorePwd")
	private String drugstorePwd;
	
	@Column(name = "drugstore_name")
	private String drugstoreName;
	
	@Column(name = "drugstore_birth")
	private String drugstoreBirth;
	
	@Column(name = "drugstore_sex")
	private String drugstoreSex;
	
	@Column(name = "drugstore_addr_main")
	private String drugstoreAddrMain;
	
	@Column(name = "drugstore_addr_detail")
	private String drugstoreAddrDetail;
	
	@Column(name = "file_key")
	private String fileKey;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "drugstore_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<RoleEntity> roles = new HashSet<>();
	
	public void addRole(RoleEntity doctorRole) {
		roles.add(doctorRole);
	}
}
