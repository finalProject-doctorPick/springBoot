package com.example.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.entity.RoleEntity;

import lombok.Data;

@Data
public class DrugstoreDTO{
	
	private Integer drugstoreId;
	
	private String drugstoreEmail;
	
	private String drugstorePwd;
	
	private String drugstoreName;
	
	private String drugstoreBirth;
	
	private String drugstoreSex;
	
	private String drugstoreAddrMain;
	
	private String drugstoreAddrDetail;
	
	private String fileKey;
	
	private Set<RoleDTO> roles = new HashSet<>();
	
	public void addRole(RoleDTO drugStoreRole) {
		roles.add(drugStoreRole);
	}
	
	public void addRoleFromEntity(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
        roles.add(roleDTO);
	}
}
