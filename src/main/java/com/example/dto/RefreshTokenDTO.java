package com.example.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class RefreshTokenDTO {
    @NotEmpty
    String refreshToken;
}