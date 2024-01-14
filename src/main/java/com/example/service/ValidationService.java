package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ValidationService {
	ResponseEntity<?> checkValue(List<String[]> checkValues);
}
