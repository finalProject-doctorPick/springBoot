package com.example.service;

import java.util.Optional;

import com.example.domain.RefreshToken;
import com.example.entity.RefreshTokenEntity;

public interface RefreshTokenService {

    public RefreshToken addRefreshToken(RefreshTokenEntity refreshToken);

    public void deleteRefreshToken(String refreshToken);

    public Optional<RefreshToken> findRefreshToken(String refreshTokenDTO);

}
