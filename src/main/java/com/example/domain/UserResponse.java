package com.example.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

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

}