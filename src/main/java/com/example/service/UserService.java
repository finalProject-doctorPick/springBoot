package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Users;

public interface UserService {

	ResponseEntity<?> signup(Users userSignupData, List<MultipartFile> file);

	ResponseEntity<?> login(Users userLoginData);
	
}
