package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.jwt.util.JwtTokenizer;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthTestController {
	private final JwtTokenizer tokenizer;
	
	@GetMapping("/hello")
	public String hello(@RequestHeader("Authorization") String token) {
		Integer userIdToken = tokenizer.getUserIdFromToken(token);
		return "Hello, " + userIdToken;
	}
}
