package com.example.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.http.ResponseEntity;

public interface MailService {

	// 메일 내용 작성 
    MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException;

    // 랜덤 인증코드 생성
    String createKey();
    
    // 메일 발송
    ResponseEntity<?> sendSimpleMessage(String to) throws Exception;
}
