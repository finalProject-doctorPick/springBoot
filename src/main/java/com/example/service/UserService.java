package com.example.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MemberLoginDTO;
import com.example.dto.UserSignupDTO;

public interface UserService {

	ResponseEntity<?> signup(UserSignupDTO userSignupDTO, List<MultipartFile> file);

	ResponseEntity<?> login(MemberLoginDTO loginDTO);
	
}
