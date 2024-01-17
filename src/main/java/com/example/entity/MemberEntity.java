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
@Table(name = "members")
public class MemberEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	// 회원ID
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer memberId;
	
    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "member_name")
    private String memberName;
    
    @Column(name = "member_birth")
    private String memberBirth;
    
    @Column(name = "member_auth")
    private String memberAuth;
    
    @Column(name = "member_sex")
    private String memberSex;
    
    @Column(name = "member_point")
    private Integer memberPoint;
    
    @Column(name = "member_tel")
    private String memberTel;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "member_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

	public void addRole(RoleEntity role) {
		roles.add(role);
	}

}