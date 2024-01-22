package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.DashBoard;
import com.example.domain.Hospital;
import com.example.domain.Member;
import com.example.domain.RefreshToken;
import com.example.domain.Users;

public interface UserService {

	// 유저 회원가입
	ResponseEntity<?> signup(Users userSignupData, List<MultipartFile> file);

	// 유저 로그인
	ResponseEntity<?> login(Users userLoginData);

	// 토근 제거
	ResponseEntity<?> deleteRefreshToken(String refreshToken);

	// refreshToken 확인 후 accessToken 재발급
	ResponseEntity<?> issueAccessToken(RefreshToken refreshToken);
	
}
