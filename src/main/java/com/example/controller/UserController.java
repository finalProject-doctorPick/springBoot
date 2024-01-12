package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.example.domain.Member;
import com.example.domain.RefreshToken;
import com.example.domain.Role;
import com.example.dto.MemberLoginDTO;
import com.example.dto.MemberLoginResponseDTO;
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
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: UserLoginDTO, bindingResult
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰 생성 및 저장
     * */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid MemberLoginDTO loginDTO, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        ResponseEntity<?> responseEntity = userService.login(loginDTO);
        return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}
