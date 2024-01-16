package com.example.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.domain.ServerResponse;
import com.example.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService{

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-12
     *  @param		: List<String[]> checkValues
     *  @return		: ResponseEntity
     * 	@explain	: 데이터 값 타입 별 체크
     * */
	public ResponseEntity<?> checkValue(List<String[]> checkValues) {
		ServerResponse response = new ServerResponse();
		 for (String[] stringArray : checkValues) {
		        String type = stringArray[0];
		        String value = stringArray[1];
		        
		        if(value == null || value.equals("")) {
		        	response.setSuccess(false);
		        	response.setMessage(type + "값을 입력해 주세요.");
		        	return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		        }

		        switch (type) {
		        	// 이메일 형식 검증
		            case "email":
		                if (!isValidEmail(value)) {
		                    response.setSuccess(false);
		                    response.setMessage("이메일 형식이 올바르지 않습니다.");
		                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		                }
		                break;

	                // 비밀번호 형식 검증
		            case "password":
		                if (!isValidPassword(value)) {
		                    response.setSuccess(false);
		                    response.setMessage("비밀번호는 영문+숫자+특수문자를 포함한 8~20자여야 합니다.");
		                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		                }
		                break;
		                
		             // 이름 형식 검증
		            case "name":
		                if (!isValidName(value)) {
		                    response.setSuccess(false);
		                    response.setMessage("이름은 영문자, 한글, 공백포함 2글자부터 15글자까지 가능합니다.");
		                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		                }
		                break;
		                
		            case "birth":
		                if (!isValidBirth(value)) {
		                    response.setSuccess(false);
		                    response.setMessage("생년월일은 8자리의 숫자로 입력해야 합니다.");
		                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		                }
		                break;

		            // 알 수 없는 유형
		            default:
		            	response.setSuccess(false);
	                    response.setMessage("알 수 없는 유형입니다.");
	                    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		        }
		    }

		    return null;
	}

	private boolean isValidEmail(String email) {
		// 이메일 형식 정규식 검증 
        String emailRegex = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        return email.matches(emailRegex);
    }
	
	private boolean isValidPassword(String password) {
        // 비밀번호 형식 정규식 검증 
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,20}$";
        return password.matches(passwordRegex);
    }
	
	private boolean isValidName(String name) {
		// 이름 형식 정규식 검증 
        String nameRegex = "^[a-zA-Z가-힣\\\\s]{2,15}";
        return name.matches(nameRegex);
    }
	
	private boolean isValidBirth(String birth) {
		// 생년월일 형식 정규식 검증 
		String birthRegex = "^[0-9]{8}$";
		return birth.matches(birthRegex);
	}
}
