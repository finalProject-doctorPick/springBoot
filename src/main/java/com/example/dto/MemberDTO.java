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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "members")
@NoArgsConstructor
@Setter
@Getter
public class MemberDTO implements Serializable{

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
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_email", referencedColumnName = "member_email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public void addRole(Role role) {
		roles.add(role);
	}

}