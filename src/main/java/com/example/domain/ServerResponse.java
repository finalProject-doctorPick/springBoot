package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerResponse {

	// 회원가입 성공 여부
    private boolean success;

    // 메시지
    private String message;
    
	// 유저 권한
	private String userAuth;
	
	// 회원 ID
    private Integer userId;
	
    // 회원 이름
    private String userName;
    
    // 토큰
    private String accessToken;
    
    // 리프레시 토큰
    private String refreshToken;
    
    // 인증키
    private String mailKey;
    
    // 이메일
    private String userEmail;
}