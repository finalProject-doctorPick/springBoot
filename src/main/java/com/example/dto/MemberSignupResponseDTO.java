package com.example.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class MemberSignupResponseDTO {
	
	@Column(name = "member_id")
    private Integer memberId;
	
	@Column(name = "member_email")
    private String memberEmail;
	
	@Column(name = "member_name")
    private String memberName;
}