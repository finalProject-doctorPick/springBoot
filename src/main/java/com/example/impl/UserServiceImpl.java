package com.example.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Member;
import com.example.domain.UserRequest;
import com.example.domain.UserResponse;
import com.example.service.DoctorService;
import com.example.service.DrugstoreService;
import com.example.service.MemberService;
import com.example.service.UserService;
import com.example.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final MemberService memberService;
    private final DoctorService doctorService;
    private final DrugstoreService drugstoreService;
    private final ValidationService validationService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: request request, List<MultipartFile> fileList
     *  @return		: ResponseEntity
     * 	@explain	: 회원 가입 절차
     * */
    @Transactional
	public ResponseEntity<?> signup(UserRequest userSignupData, List<MultipartFile> fileList) {
    	UserResponse response = new UserResponse();
    	List<String[]> checkValues = new ArrayList<>();
    	
    	String email = userSignupData.getUserEmail();
		String auth  = userSignupData.getUserAuth();

		// 데이터 체크 리스트 생성 및 체크
    	checkValues.add(new String[]{"email", email});
    	checkValues.add(new String[]{"password", userSignupData.getUserPwd()});
    	checkValues.add(new String[]{"name", userSignupData.getUserName()});
    	checkValues.add(new String[]{"birth", userSignupData.getUserBirth()});
        
    	ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
    	
    	// validation 에러가 있을 경우 처리
        if (validationResponse != null) {
        	System.out.println(validationResponse);
            return validationResponse;
        }
        
		// 이메일 중복 확인
    	if (isEmailDuplicate(email)) {
            response.setSuccess(false);
            response.setMessage("중복된 이메일이 존재합니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
    	// 의사 및 약국 가입 시 파일 필수
    	if (!auth.equals("N") && (fileList == null || fileList.isEmpty())) {
            response.setSuccess(false);
            response.setMessage("의사 또는 약국 가입 시 증명파일 등록이 필수입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    	
    	switch (userSignupData.getUserAuth()) {
		case "N" :
			return new ResponseEntity<>(memberService.registerMember(userSignupData), HttpStatus.CREATED);
		case "D" :
			return new ResponseEntity<>(doctorService.registerDoctor(userSignupData, fileList), HttpStatus.CREATED);
		case "S" :
			return new ResponseEntity<>(drugstoreService.registerDrugstore(userSignupData, fileList), HttpStatus.CREATED);
		default : 
			response.setSuccess(false);
			response.setMessage("가입 유형이 올바르지 않습니다. // 일반(N), 의사(D), 약국(S)");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: UserLoginDTO
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰 생성 및 저장
     * */
    @Transactional
	public ResponseEntity<?> login(UserRequest userLoginData) {    	
    	UserResponse response = new UserResponse();
    	List<String[]> checkValues = new ArrayList<>();
    	
    	System.out.println("****************************");
    	System.out.println("checkValue 전 checkValues 값: " + checkValues.stream().map(Arrays::toString).collect(Collectors.toList()));
    	System.out.println("****************************");

    	String email = userLoginData.getUserEmail();
		String pwd = userLoginData.getUserPwd();
		
		// 데이터 체크 리스트 생성 및 체크
    	checkValues.add(new String[]{"email", email});
    	checkValues.add(new String[]{"password", pwd});
    	
		ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
    	
    	// validation 에러가 있을 경우 처리
        if (validationResponse != null) {
        	System.out.println(validationResponse);
            return validationResponse;
        }
        
        // 일반 회원 체크
        Member member = memberService.findByMemberEmail(email, pwd);
        if (member != null) {
            // 로그인 성공
        	response.setSuccess(true);
        	response.setMessage("로그인이 되었습니다." + (member.getMemberAuth().equals("N") ? " (일반회원)" : " (관리자)"));
        	response.setUserAuth(member.getMemberAuth());
        	response.setUserId(member.getMemberId());
        	response.setUserName(member.getMemberName());
            return new ResponseEntity<>("로그인이 되었습니다. (일반회원)", HttpStatus.OK);
        }
        
        // 이메일로 Doctor 찾기
        Doctor doctor = doctorService.findByDoctorEmail(email, pwd);
        if (doctor != null) {
            // 로그인 성공
        	response.setSuccess(true);
        	response.setSuccess(true);
            return new ResponseEntity<>("로그인이 되었습니다. (의사)", HttpStatus.OK);
        }

        // 이메일로 DrugStore 찾기
        Drugstore drugstore = drugstoreService.findByDrugstoreEmail(email, pwd);
        if (drugstore != null) {
            // 로그인 성공
        	return new ResponseEntity<>("로그인이 되었습니다. (약국)", HttpStatus.OK);
        }

        // 로그인 실패
        response.setSuccess(false);
        response.setMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: String email
     *  @return		: boolean
     * 	@explain	: 일반회원, 의사, 약국 이메일 중복 확인
     * */
    private boolean isEmailDuplicate(String email) {
        return memberService.existsByMemberEmail(email)
                || doctorService.existsByDoctorEmail(email)
                || drugstoreService.existsByDrugstoreEmail(email);
    }
}
