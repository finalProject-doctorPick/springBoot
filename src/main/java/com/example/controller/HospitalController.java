package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.jwt.util.JwtTokenizer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospital")
public class HospitalController {
	private final JwtTokenizer tokenizer;
	
	@GetMapping("/search")
	public String hello(@RequestHeader("Authorization") String token) {
		Integer userIdToken = tokenizer.getUserIdFromToken(token);
		return "Hello, " + userIdToken;
	}
}
