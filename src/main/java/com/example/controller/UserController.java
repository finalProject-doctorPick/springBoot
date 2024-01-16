package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Users;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-10
     *  @param		: UserSignupDTO, List<MultipartFile>
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
     *  @param		: UserLoginDTO, bindingResult
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰 생성 및 저장
     * */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users userLoginData, BindingResult bindingResult) {
        System.out.println("***************************");
        System.out.println("users/login 진입 성공 !!!");
        System.out.println("userLoginData 값 : " + userLoginData.toString());
        System.out.println("***************************");
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        ResponseEntity<?> responseEntity = userService.login(userLoginData);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
    
}
