package com.example.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.RefreshToken;
import com.example.entity.RefreshTokenEntity;
import com.example.repository.RefreshTokenRepository;
import com.example.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService{
	
    private final RefreshTokenRepository refreshTokenRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public RefreshToken addRefreshToken(RefreshTokenEntity refreshTokenEntity) {
    	RefreshToken token = modelMapper.map(refreshTokenRepository.save(refreshTokenEntity), RefreshToken.class);
        return token;
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
    public Optional<RefreshTokenEntity> findRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByValue(refreshToken);
    }
}
