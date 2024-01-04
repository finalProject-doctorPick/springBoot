package com.example.dto;

import lombok.Data;

@Data
public class MemberSignupResponseDTO {
	
    private Integer memberId;
	
    private String memberEmail;
	
    private String memberName;
}