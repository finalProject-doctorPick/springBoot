package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.RefreshToken;
import com.example.domain.SMS;
import com.example.domain.Users;
import com.example.service.MailService;
import com.example.service.SMSService;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final MailService mailService;
	private final SMSService smsService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-10
     *  @param		: Users, List<MultipartFile>
     *  @return		: ResponseEntity
     * 	@explain	: 회원가입
     * */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @ModelAttribute Users userSignupData,
            @RequestPart(name = "fileList", required = false) List<MultipartFile> fileList) {

    	ResponseEntity<?> responseEntity = userService.signup(userSignupData, fileList);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: Users, bindingResult
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰 생성 및 저장
     * */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users userLoginData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        ResponseEntity<?> responseEntity = userService.login(userLoginData);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-17
     *  @param		: String refreshToken
     *  @return		: ResponseEntity
     * 	@explain	: logout > Refresh Token 제거
     * */
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken) {
    	ResponseEntity<?> responseEntity = userService.deleteRefreshToken(refreshToken);
    	return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-17
     *  @param		: RefreshToken refreshToken
     *  @return		: ResponseEntity
     * 	@explain	: 유저 및 Refresh Token 유효성 체크 후 Access Token 발급
     * */
    @PostMapping("/refreshToken")
    public ResponseEntity<?> issueAccessToken(@RequestBody RefreshToken refreshToken) {
    	
    	ResponseEntity<?> responseEntity = userService.issueAccessToken(refreshToken);
    	return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-23
     *  @param		: String email
     *  @return		: ResponseEntity
     * 	@explain	: 이메일 인증코드 발급 및 전송
     * */ 
    @PostMapping("/mailConfirm")
    public ResponseEntity<?> mailConfirm(@RequestParam String userEmail) throws Exception {
        ResponseEntity<?> responseEntity = mailService.sendSimpleMessage(userEmail);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-24
     *  @param		: SMS smsTarget
     *  @return		: ResponseEntity
     * 	@explain	: SMS 전송
     * */ 
    @PostMapping("/smsSend")
    public ResponseEntity<?> sendSms(@RequestBody SMS smsTarget) {
        
    	ResponseEntity<?> responseEntity = smsService.sendSMSMessage(smsTarget.getUserTel(), smsTarget.getSendMessage());
    	return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    
}
