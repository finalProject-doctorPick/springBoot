package com.example.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.entity.RoleEntity;

import lombok.Data;

@Data
public class MemberDTO{

	private Integer memberId;
	
    private String memberEmail;

    private String memberPwd;

    private String memberName;
    
    private String memberBirth;
    
    private String memberAuth;
    
    private String memberSex;
    
    private Integer memberPoint;
    
    private Set<RoleDTO> roles = new HashSet<>();

	public void addRole(RoleDTO role) {
		roles.add(role);
	}

	public void addRoleFromEntity(RoleEntity roleEntity) {
        RoleDTO roleDTO = new RoleDTO();
        roles.add(roleDTO);
    }

}