package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.RefreshToken;
import com.example.dto.RefreshTokenDTO;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenDTO, Long> {
    Optional<RefreshToken> findByValue(String value);

	RefreshToken save(RefreshToken refreshToken);
}