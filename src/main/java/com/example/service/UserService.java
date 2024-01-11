package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserSignupDTO;

public interface UserService {

	@Transactional
	ResponseEntity<?> signup(UserSignupDTO userSignupDTO, List<MultipartFile> file);
	
}
