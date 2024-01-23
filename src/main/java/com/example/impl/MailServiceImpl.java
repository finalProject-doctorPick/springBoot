package com.example.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.domain.ServerResponse;
import com.example.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	
	private final JavaMailSender emailSender;
	
	private String ePw;
	
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-23
     *  @param		: String email
     *  @return		: ResponseEntity
     * 	@explain	: 이메일 인증코드 발급 및 전송
     * */ 
	@Transactional
	public ResponseEntity<?> sendSimpleMessage(String to) throws Exception {
		ServerResponse response = new ServerResponse();
		
		// 인증코드 생성
		ePw = createKey();

        MimeMessage message = creatMessage(to);

        // 예외처리
        try { 
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        
        response.setSuccess(true);
        response.setMessage("인증코드가 발송되었습니다. 메일함에서 확인 부탁드립니다! :)");
        response.setMailKey(ePw);

        return new ResponseEntity<>(response, HttpStatus.OK);  
	}
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-23
     *  @param		: String to
     *  @return		: MimeMessage
     * 	@explain	: 이메일 내용 작성 및 이메일 발송
     * */ 
	@Transactional
	public MimeMessage creatMessage(String to) throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = emailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    helper.addTo(to);
	    helper.setSubject("[DOCTORPICK] 회원 가입을 위한 이메일 인증코드 입니다");

	    String msgg = "";
	    msgg += "<img src='cid:logo'/>";
	    msgg += "<h1 style='color:mint'>안녕하세요 !</h1>";
	    msgg += "<h1>의료 플랫폼 DOCTORPICK 입니다 :)</h1>";
	    msgg += "<br>";
	    msgg += "<p>아래 인증코드를 회원가입 페이지에 입력해주세요</p>";
	    msgg += "<br>";
	    msgg += "<br>";
	    msgg += "<div align='center' style='border:1px solid black'>";
	    msgg += "<h3 style='color:blue'>회원가입 인증코드 입니다</h3>";
	    msgg += "<div style='font-size:130%'>";
	    msgg += "<strong>" + ePw + "</strong></div><br/>";
	    msgg += "</div>";

	    helper.setText(msgg, true);

	    ClassPathResource resource = new ClassPathResource("image/Logo.png");
	    helper.addInline("logo", resource);

	    helper.setFrom(new InternetAddress("doctorpick@naver.com", "DOCTORPICK_MASTER"));

	    return message;
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-23
     *  @param		: void
     *  @return		: String
     * 	@explain	: 이메일 인증코드용 6자리 난수 생성
     * */ 
	@Transactional
	public String createKey() {
        return String.valueOf((int)(Math.random() * (90000)) + 100000);
	}

	
	
}
