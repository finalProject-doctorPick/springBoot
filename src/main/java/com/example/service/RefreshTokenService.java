package com.example.service;

import java.util.Optional;

import com.example.domain.RefreshToken;
import com.example.entity.RefreshTokenEntity;

public interface RefreshTokenService {
	
	/**
	 *	****************MyBatis********************* 
	 * */
	
	
	/**
	 *	*******************JPA*********************** 
	 * */
	// refreshToken 추가
    public RefreshToken addRefreshToken(RefreshTokenEntity refreshToken);

    // refreshToken 삭제
    public void deleteRefreshToken(String refreshToken);

    // refreshToken 조회
    public Optional<RefreshTokenEntity> findRefreshToken(String refreshTokenDTO);

}
