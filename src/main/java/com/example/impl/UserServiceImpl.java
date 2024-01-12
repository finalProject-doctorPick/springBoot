package com.example.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.MemberLoginDTO;
import com.example.dto.UserSignupDTO;
import com.example.dto.UserSignupResponseDTO;
import com.example.service.DoctorService;
import com.example.service.DrugstoreService;
import com.example.service.MemberService;
import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final MemberService memberService;
    private final DoctorService doctorService;
    private final DrugstoreService drugstoreService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: UserSignupDTO userSignupDTO, List<MultipartFile> fileList
     *  @return		: ResponseEntity
     * 	@explain	: 회원 가입 절차
     * */
    @Transactional
	public ResponseEntity<?> signup(UserSignupDTO userSignupDTO, List<MultipartFile> fileList) {
    	
    	UserSignupResponseDTO response = new UserSignupResponseDTO();
    	
    	String email = userSignupDTO.getUserEmail();
		String auth = userSignupDTO.getUserAuth();
		
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
    	
    	switch (userSignupDTO.getUserAuth()) {
		case "N" :
			return new ResponseEntity<>(memberService.registerMember(userSignupDTO), HttpStatus.CREATED);
		case "D" :
			return new ResponseEntity<>(doctorService.registerDoctor(userSignupDTO, fileList), HttpStatus.CREATED);
		case "S" :
			return new ResponseEntity<>(drugstoreService.registerDrugstore(userSignupDTO, fileList), HttpStatus.CREATED);
		default : 
			response.setSuccess(false);
			response.setMessage("가입 유형이 올바르지 않습니다. // 일반(N), 의사(D), 약국(S)");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
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

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: UserLoginDTO
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰 생성 및 저장
     * */
	public ResponseEntity<?> login(MemberLoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
