package com.example.dto;

import lombok.Data;

@Data
public class RefreshTokenDTO {
    private Long id;
    
    private String userEmail;
    
    private String value;
}