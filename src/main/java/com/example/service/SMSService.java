package com.example.service;

import org.springframework.http.ResponseEntity;

public interface SMSService {
    // SMS 전송
	ResponseEntity<?> sendSMSMessage(String userTel, String sendMessage);
}
