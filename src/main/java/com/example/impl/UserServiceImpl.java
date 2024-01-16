package com.example.impl;

import java.util.ArrayList;
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
import com.example.domain.Role;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.dto.DrugstoreDTO;
import com.example.dto.MemberDTO;
import com.example.dto.RefreshTokenDTO;
import com.example.dto.RoleDTO;
import com.example.security.jwt.util.JwtTokenizer;
import com.example.service.DoctorService;
import com.example.service.DrugstoreService;
import com.example.service.MemberService;
import com.example.service.RefreshTokenService;
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
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenizer jwtTokenizer;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: request request, List<MultipartFile> fileList
     *  @return		: ResponseEntity
     * 	@explain	: 회원 가입 절차
     * */
    @Transactional
	public ResponseEntity<?> signup(Users userSignupData, List<MultipartFile> fileList) {
    	ServerResponse response = new ServerResponse();
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
	public ResponseEntity<?> login(Users userLoginData) {    	
    	ServerResponse response = new ServerResponse();
    	List<String[]> checkValues = new ArrayList<>();

    	String email = userLoginData.getUserEmail();
		String pwd = userLoginData.getUserPwd();
		
		// 데이터 체크 리스트 생성 및 체크
    	checkValues.add(new String[]{"email", email});
    	checkValues.add(new String[]{"password", pwd});
    	
    	System.out.println("***************************");
        System.out.println("userService > login 진입 성공 !!!");
        System.out.println("checkValue 값 : " + checkValues.toString());
        System.out.println("***************************");
        
		ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
    	
    	// validation 에러가 있을 경우 처리
        if (validationResponse != null) {
            return validationResponse;
        }
        
        // 일반 회원 체크
        Member member = memberService.findByMemberEmail(email, pwd);
        if (member != null) {
            // 로그인 성공
            ResponseEntity<?> loginResponse = tokensIssuance(member.getMemberEmail(),member.getMemberPwd(), "N");
            return loginResponse;
        }
        
        // 이메일로 Doctor 찾기
        Doctor doctor = doctorService.findByDoctorEmail(email, pwd);
        if (doctor != null && doctor.getDoctorConfirmYn().equals("Y")) {
            // 로그인 성공
        	ResponseEntity<?> loginResponse = tokensIssuance(doctor.getDoctorEmail(),doctor.getDoctorPwd(), "D");
            return loginResponse;
        }

        // 이메일로 DrugStore 찾기
        Drugstore drugstore = drugstoreService.findByDrugstoreEmail(email, pwd);
        if (drugstore != null && drugstore.getDrugstoreConfirmYn().equals("Y")) {
            // 로그인 성공
        	ResponseEntity<?> loginResponse = tokensIssuance(drugstore.getDrugstoreEmail(),drugstore.getDrugstorePwd(), "S");
            return loginResponse;
        }

        // 로그인 실패
        response.setSuccess(false);
        if( (doctor != null && doctor.getDoctorConfirmYn().equals("N")) ) {
        	response.setMessage("의사 승인 처리가 되지 않았습니다.");
        }else if( (drugstore != null && drugstore.getDrugstoreConfirmYn().equals("N")) ){
        	response.setMessage("약국 승인 처리가 되지 않았습니다.");
        }else {
        	response.setMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        
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
    
    private ResponseEntity<?> tokensIssuance(String email, String pwd, String userAuth) {
    	ServerResponse response = new ServerResponse();
    	System.out.println("***************************");
        System.out.println("tokensIssuance 진입 성공 !!!");
        System.out.println("***************************");
    	switch (userAuth) {
			case "D" :
				// doctor & role 조회 
				Doctor d = doctorService.findByDoctorEmail(email, pwd);
				
				// List<Role> -> List<String>
				List<String> doctorRoles = d.getRoles().stream().map(Role::getRoles).collect(Collectors.toList());
				
				// JWT 토큰 생성
				String doctorAccessToken = jwtTokenizer.createAccessToken(d.getDoctorId(), d.getDoctorEmail(), doctorRoles);
		        String doctorRefreshToken = jwtTokenizer.createRefreshToken(d.getDoctorId(), d.getDoctorEmail(), doctorRoles);
			
		        // RefreshToken 생성 및 저장
		        RefreshTokenDTO doctorRefreshTokenEntity = new RefreshTokenDTO();
		        doctorRefreshTokenEntity.setValue(doctorRefreshToken);
		        doctorRefreshTokenEntity.setUserEmail(email);
		        refreshTokenService.addRefreshToken(doctorRefreshTokenEntity);
		        
		        response.setSuccess(true);
		        response.setMessage("로그인이 되었습니다. (의사회원)");
		        response.setAccessToken(doctorAccessToken);
		        response.setRefreshToken(doctorRefreshToken);
		        response.setUserId(d.getDoctorId());
		        response.setUserName(d.getDoctorName());
		        response.setUserAuth("D");
		        
		        System.out.println("*****************************************");
	        	System.out.println("tokensIssuance > 의사회원 리턴 전 toString : " + response.toString());
	        	System.out.println("*****************************************");
		        return new ResponseEntity<>(response, HttpStatus.OK);  
		        
			case "S" :
				
				// drugStore & role 조회 
				Drugstore s = drugstoreService.findByDrugstoreEmail(email, pwd);
				
				// List<Role> -> List<String>
				List<String> storeRoles = s.getRoles().stream().map(Role::getRoles).collect(Collectors.toList());
				
				// JWT 토큰 생성
				String storeAccessToken = jwtTokenizer.createAccessToken(s.getDrugstoreId(), s.getDrugstoreEmail(), storeRoles);
		        String storeRefreshToken = jwtTokenizer.createRefreshToken(s.getDrugstoreId(), s.getDrugstoreEmail(), storeRoles);
			
		        // RefreshToken 생성 및 저장
		        RefreshTokenDTO storeRefreshTokenEntity = new RefreshTokenDTO();
		        storeRefreshTokenEntity.setValue(storeRefreshToken);
		        storeRefreshTokenEntity.setUserEmail(email);
		        refreshTokenService.addRefreshToken(storeRefreshTokenEntity);
		        
		        response.setSuccess(true);
		        response.setMessage("로그인이 되었습니다. (약국회원)");
		        response.setAccessToken(storeAccessToken);
		        response.setRefreshToken(storeRefreshToken);
		        response.setUserId(s.getDrugstoreId());
		        response.setUserName(s.getDrugstoreName());
		        response.setUserAuth("S");
		        
		        System.out.println("*****************************************");
	        	System.out.println("tokensIssuance > 약국회원 리턴 전 toString : " + response.toString());
	        	System.out.println("*****************************************");
		        return new ResponseEntity<>(response, HttpStatus.OK);  
			default:
				// member & role 조회 
				Member m = memberService.findByMemberEmail(email, pwd);
				System.out.println("*****************************************");
				System.out.println("tokensIssuance > Member m.getRoles 값 : " + m.getRoles());
				System.out.println("*****************************************");
				
				// List<Role> -> List<String>
				List<String> roles = m.getRoles().stream().map(Role::getRoles).collect(Collectors.toList());
				
				// JWT 토큰 생성
				String memberAccessToken = jwtTokenizer.createAccessToken(m.getMemberId(), m.getMemberEmail(), roles);
		        String memberRefreshToken = jwtTokenizer.createRefreshToken(m.getMemberId(), m.getMemberEmail(), roles);
			
		        // RefreshToken 생성 및 저장
		        RefreshTokenDTO memberRefreshTokenEntity = new RefreshTokenDTO();
		        memberRefreshTokenEntity.setValue(memberRefreshToken);
		        memberRefreshTokenEntity.setUserEmail(email);
		        refreshTokenService.addRefreshToken(memberRefreshTokenEntity);
		        
		        response.setSuccess(true);
		        response.setMessage("로그인이 되었습니다." + (m.getMemberAuth().equals("N") ? " (일반회원)" : " (관리자)"));
		        response.setAccessToken(memberAccessToken);
		        response.setRefreshToken(memberRefreshToken);
		        response.setUserId(m.getMemberId());
		        response.setUserName(m.getMemberName());
		        response.setUserAuth(m.getMemberAuth());
		        
		        return new ResponseEntity<>(response, HttpStatus.OK);
			}	
    }
}
