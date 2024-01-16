package com.example.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.RefreshToken;
import com.example.dto.RefreshTokenDTO;
import com.example.repository.RefreshTokenRepository;
import com.example.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{
	
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public RefreshToken addRefreshToken(RefreshTokenDTO refreshTokenDTO) {
    	RefreshToken result = refreshTokenRepository.save(refreshTokenDTO);
    	result.setId(result.getId());
    	result.setUserEmail(result.getUserEmail());
    	result.setValue(result.getValue());
        return result;
    }

//    @Transactional
//    public void deleteRefreshToken(String refreshToken) {
//        refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenRepository::delete);
//    }
    
    @Transactional
    public void deleteRefreshToken(String refreshToken) {
        refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenEntity -> {
            Long id = refreshTokenEntity.getId();
            refreshTokenRepository.deleteById(id);
        });
    }


    @Transactional(readOnly = true)
    public Optional<RefreshToken> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }
}
