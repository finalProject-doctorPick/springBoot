package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ValidationService {
	
	// 유효성 체크
	ResponseEntity<?> checkValue(List<String[]> checkValues);
}
