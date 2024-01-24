package com.example.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Doctor;
import com.example.domain.Drugstore;
import com.example.domain.Member;
import com.example.domain.RefreshToken;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.DoctorEntity;
import com.example.entity.DrugstoreEntity;
import com.example.entity.MemberEntity;
import com.example.entity.RefreshTokenEntity;
import com.example.entity.RoleEntity;
import com.example.repository.RefreshTokenRepository;
import com.example.security.jwt.util.JwtTokenizer;
import com.example.service.DoctorService;
import com.example.service.DrugstoreService;
import com.example.service.MemberService;
import com.example.service.RefreshTokenService;
import com.example.service.UserService;
import com.example.service.ValidationService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final MemberService memberService;
    private final DoctorService doctorService;
    private final DrugstoreService drugstoreService;
    private final ValidationService validationService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
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
    @Transactional
	public ResponseEntity<?> login(Users userLoginData) {    	
    	ServerResponse response = new ServerResponse();
    	List<String[]> checkValues = new ArrayList<>();

    	String email = userLoginData.getUserEmail();
		String pwd = userLoginData.getUserPwd();
		
		// 데이터 체크 리스트 생성 및 체크
    	checkValues.add(new String[]{"email", email});
    	checkValues.add(new String[]{"password", pwd});
        
		ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
    	
    	// validation 에러가 있을 경우 처리
        if (validationResponse != null) {
            return validationResponse;
        }
        
        // 일반 회원, 의사, 약국 순으로 확인
        Member member = memberService.findByMemberEmailAndPwd(email, pwd);
        Doctor doctor = doctorService.validateDoctorEmailAndPwd(email, pwd);
        Drugstore drugstore = drugstoreService.validateDrugstoreEmail(email, pwd);

        if (member != null || doctor != null || drugstore != null) {
            // 로그인 성공
            String userAuth;
            String userName;
            Integer userId;

            if (doctor != null) {
                if (!doctor.getDoctorConfirmYn().equals("Y")) {
                    response.setSuccess(false);
                    response.setMessage("의사 승인 처리가 되지 않았습니다.");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                userAuth = "D";
                userName = doctor.getDoctorName();
                userId = doctor.getDoctorId();
            } else if (drugstore != null) {
                if (!drugstore.getDrugstoreConfirmYn().equals("Y")) {
                    response.setSuccess(false);
                    response.setMessage("약국 승인 처리가 되지 않았습니다.");
                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }
                userAuth = "S";
                userName = drugstore.getDrugstoreName();
                userId = drugstore.getDrugstoreId();
            } else {
                userAuth = member.getMemberAuth();
                userName = member.getMemberName();
                userId = member.getMemberId();
            }

            ResponseEntity<?> loginResponse = tokensIssuance(email, userAuth, userName, userId);
            return loginResponse;
        }

        // 로그인 실패
        response.setSuccess(false);
        response.setMessage("이메일 또는 비밀번호가 올바르지 않습니다.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    private ResponseEntity<?> tokensIssuance(String email, String userAuth, String userName, Integer userId) {
    	ServerResponse response = new ServerResponse();
    	
    	// 해당 유저 & role 조회
        RoleEntity userRole = getUserRole(userAuth, email);
        
        List<String> roles = getRoleNames(userRole);

        // JWT 토큰 생성
        String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);
        String refreshToken = jwtTokenizer.createRefreshToken(userId, email, roles);

        // RefreshToken 생성 및 저장
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setUserEmail(email);
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        response.setSuccess(true);
        response.setMessage("DOCTORPICK에 방문해 주신 " + userName + "님 환영합니다.");
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setUserId(userId);
        response.setUserName(userName);
        response.setUserAuth(userAuth);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private RoleEntity getUserRole(String userAuth, String email) {
        switch (userAuth) {
            case "D":
                return getFirstRole(doctorService.getDoctor(email).getRoles());
            case "S":
                return getFirstRole(drugstoreService.getDrugstore(email).getRoles());
            default:
                return getFirstRole(memberService.getMember(email).getRoles());
        }
    }
    
    private RoleEntity getFirstRole(Set<RoleEntity> roles) {
        if (roles != null && !roles.isEmpty()) {
            return roles.iterator().next();
        }
        return null;
    }
    
    private List<String> getRoleNames(RoleEntity userRole) {
        if (userRole != null) {
            return Collections.singletonList(userRole.getRoles());
        }
        return Collections.emptyList();
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-17
     *  @param		: RefreshToken, bindingResult
     *  @return		: ResponseEntity
     * 	@explain	: logout > Refresh Token 제거
     * */
    @Transactional
	public ResponseEntity<?> deleteRefreshToken(String refreshToken) {
    	ServerResponse response = new ServerResponse();
    	 refreshTokenRepository.findByValue(refreshToken).ifPresent(refreshTokenEntity -> {
             String userEmail = refreshTokenEntity.getUserEmail();
             System.out.println("deleteRefreshToken 전 email 값 : " + userEmail);
             refreshTokenRepository.deleteByUserEmail(userEmail);
             response.setSuccess(true);
             response.setMessage(userEmail + " 님의 토큰이 삭제 되었습니다.");
         });
         
         return new ResponseEntity<>(response, HttpStatus.OK);
	}
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: refreshTokenDTO
     *  @return		: ResponseEntity
     * 	@explain	: 유저 및 Refresh Token 유효성 체크 후 Access Token 발급
     * */
	@Transactional
	public ResponseEntity<?> issueAccessToken(RefreshToken refreshToken) {
		ServerResponse response = new ServerResponse();
		Integer userId = 0;
		String userName = "";

		refreshTokenService.findRefreshToken(refreshToken.getRefreshToken())
				.orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));
		
		Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());
		String email= (String) claims.getSubject();
		List<String> roles = (List) claims.get("roles");

		System.out.println("issueAccessToken > email 값 : " + email);
		
		
		MemberEntity m = memberService.getMember(email);
		DoctorEntity d = doctorService.getDoctor(email);
		DrugstoreEntity s = drugstoreService.getDrugstore(email);
		if(m != null) {
			userId = m.getMemberId();
			userName = m.getMemberName();
		}else if( d != null) {
			userId = d.getDoctorId();
			userName = d.getDoctorName();
		}else {
			userId = s.getDrugstoreId();
			userName = s.getDrugstoreName();
		}
		
		String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);
		
		response.setSuccess(true);
		response.setMessage("token 재발급이 완료 되었습니다.");
		response.setAccessToken(accessToken);
		response.setRefreshToken(refreshToken.getRefreshToken());
		response.setUserId(userId);
		response.setUserName(userName);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
