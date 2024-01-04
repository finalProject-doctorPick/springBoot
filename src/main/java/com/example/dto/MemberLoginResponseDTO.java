package com.example.dto;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDTO {
	
    private String accessToken;
    
    private String refreshToken;
    
    @Column(name = "member_id")
    private Integer memberId;
    
    @Column(name = "member_name")
    private String memberName;
    
    @Column(name = "member_auth")
    private String memberAuth;
}