package com.example.service;

import java.util.Optional;

import com.example.domain.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken addRefreshToken(RefreshToken refreshToken);

    public void deleteRefreshToken(String refreshToken);

    public Optional<RefreshToken> findRefreshToken(String refreshToken);

}
