package com.example.domain;

import lombok.Data;


@Data
public class RefreshToken {
	private Long id;
	
    private String refreshToken;
    
    private Integer userId;
    
    private String value;
}