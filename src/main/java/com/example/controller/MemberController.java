package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.MemberService;
import com.example.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: refreshTokenDTO
     *  @return		: ResponseEntity
     * 	@explain	: 유저 및 Refresh Token 유효성 체크 후 Access Token 발급
     * 
     * */
//    @PostMapping("/refreshToken")
//    public ResponseEntity requestRefresh(@RequestBody RefreshToken param) {
//        RefreshToken refreshToken = refreshTokenService.findRefreshToken(param.getRefreshToken()).orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));
//        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());
//
//        Integer userId = (Integer)claims.get("userId");
//
//        MemberDTO member = memberService.getMember(userId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
//
//
//        List roles = (List) claims.get("roles");
//        String email = claims.getSubject();
//
//        String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);
//
//        MemberLoginResponseDTO loginResponse = MemberLoginResponseDTO.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken.getRefreshToken())
//                .memberId(member.getMemberId())
//                .memberName(member.getMemberName())
//                .build();
//        return new ResponseEntity(loginResponse, HttpStatus.OK);
//    }
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-18
     *  @param		: Users userData
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 최근 진료 내역
     * */
    @GetMapping("/currentHistory")
    public ResponseEntity<?> memberCurrentHistory(@RequestParam Integer memberId){
    	
    	List<?> list = memberService.getMemberCurrntHistory(memberId);
    	System.out.println("list return 전 size : " + list.size());
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
}