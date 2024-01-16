package com.example.service;

import java.util.Optional;

import com.example.domain.RefreshToken;
import com.example.dto.RefreshTokenDTO;

public interface RefreshTokenService {

    public RefreshToken addRefreshToken(RefreshTokenDTO refreshToken);

    public void deleteRefreshToken(String refreshToken);

    public Optional<RefreshToken> findRefreshToken(String refreshTokenDTO);

}
