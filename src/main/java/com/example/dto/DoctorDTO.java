package com.example.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.entity.RoleEntity;

import lombok.Data;

@Data
public class DoctorDTO{
	
	private Integer doctorId;
	
	private String doctorEmail;

	private String doctorPwd;

	private String doctorName;

	private String doctorBirth;

	private String doctorSex;

	private String doctorAddrMain;

	private String doctorAddrDetail;

	private String fileKey;

	private Set<RoleDTO> roles = new HashSet<>();
	
	public void addRole(RoleDTO doctorRole) {
		roles.add(doctorRole);
	}

	public void addRoleFromEntity(RoleEntity roleEntity) {
		RoleDTO roleDTO = new RoleDTO();
        roles.add(roleDTO);
	}
}
