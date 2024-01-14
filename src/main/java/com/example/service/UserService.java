package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.UserRequest;

public interface UserService {

	ResponseEntity<?> signup(UserRequest userSignupData, List<MultipartFile> file);

	ResponseEntity<?> login(UserRequest userLoginData);
	
}
