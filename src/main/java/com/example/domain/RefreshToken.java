package com.example.domain;

import lombok.Data;


@Data
public class RefreshToken {
	private Long id;
	
    private String refreshToken;
    
    private String userEmail;
    
    private String value;
}