package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.UserSignupDTO;
import com.example.dto.UserSignupResponseDTO;
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
            @ModelAttribute UserSignupDTO userSignupDTO,
            @RequestPart(name = "fileList", required = false) List<MultipartFile> fileList,
            BindingResult bindingResult) {

    	ResponseEntity<?> responseEntity = userService.signup(userSignupDTO, fileList);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}
