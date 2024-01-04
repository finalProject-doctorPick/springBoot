package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Member;
import com.example.domain.RefreshToken;
import com.example.domain.Role;
import com.example.dto.MemberLoginDTO;
import com.example.dto.MemberLoginResponseDTO;
import com.example.dto.MemberSignupDTO;
import com.example.dto.MemberSignupResponseDTO;
import com.example.dto.RefreshTokenDTO;
import com.example.security.jwt.util.JwtTokenizer;
import com.example.service.MemberService;
import com.example.service.RefreshTokenService;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

    private final JwtTokenizer jwtTokenizer;
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: MemberSignupDTO
     *  @return		: ResponseEntity
     * 	@explain	: 회원가입
     * 
     * */
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody @Valid MemberSignupDTO memberSignupDTO, BindingResult bindingResult) {
    	
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        
        Member member = new Member();
        
        member.setMemberEmail(memberSignupDTO.getMemberEmail());
        member.setMemberName(memberSignupDTO.getMemberName());
        member.setMemberPwd(passwordEncoder.encode(memberSignupDTO.getMemberPwd()));
        member.setMemberBirth(memberSignupDTO.getMemberBirth());
        member.setMemberAuth(memberSignupDTO.getMemberAuth());
        member.setMemberSex(memberSignupDTO.getMemberSex());
        member.setMemberPoint(memberSignupDTO.getMemberPoint());

        // 회원가입
        Member saveMember = memberService.addMember(member);
        
        MemberSignupResponseDTO memberSignupResponse = new MemberSignupResponseDTO();
        memberSignupResponse.setMemberId(saveMember.getMemberId());
        memberSignupResponse.setMemberName(saveMember.getMemberName());
        memberSignupResponse.setMemberEmail(saveMember.getMemberEmail());
        
        return new ResponseEntity(memberSignupResponse, HttpStatus.CREATED);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: MemberLoginDTO, bindingResult
     *  @return		: ResponseEntity
     * 	@explain	: login > 필요 토큰들 생성 및 저장
     * 
     * */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid MemberLoginDTO loginDTO, BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // email이 없을 경우 Exception
        Member member = memberService.findByEmail(loginDTO.getMemberEmail());
        if(!passwordEncoder.matches(loginDTO.getMemberPwd(), member.getMemberPwd())){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
        
        // List<Role> ===> List<String>
        List<String> roles = member.getRoles().stream().map(Role::getMemberName).collect(Collectors.toList());

        // JWT토큰 생성
        String accessToken = jwtTokenizer.createAccessToken(member.getMemberId(), member.getMemberEmail(), roles);
        String refreshToken = jwtTokenizer.createRefreshToken(member.getMemberId(), member.getMemberEmail(), roles);

        // RefreshToken 생성 및 저장
        RefreshToken refreshTokenEntity = new RefreshToken();
        refreshTokenEntity.setValue(refreshToken);
        refreshTokenEntity.setMemberId(member.getMemberId());
        refreshTokenService.addRefreshToken(refreshTokenEntity);

        MemberLoginResponseDTO loginResponse = MemberLoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .memberAuth(member.getMemberAuth())
                .build();
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: refreshTokenDTO
     *  @return		: ResponseEntity
     * 	@explain	: logout > Refresh Token 제거
     * 
     * */
    @DeleteMapping("/logout")
    public ResponseEntity logout(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        refreshTokenService.deleteRefreshToken(refreshTokenDTO.getRefreshToken());
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-02
     *  @param		: refreshTokenDTO
     *  @return		: ResponseEntity
     * 	@explain	: 유저 및 Refresh Token 유효성 체크 후 Access Token 발급
     * 
     * */
    @PostMapping("/refreshToken")
    public ResponseEntity requestRefresh(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        RefreshToken refreshToken = refreshTokenService.findRefreshToken(refreshTokenDTO.getRefreshToken()).orElseThrow(() -> new IllegalArgumentException("Refresh token not found"));
        Claims claims = jwtTokenizer.parseRefreshToken(refreshToken.getValue());

        Integer userId = (Integer)claims.get("userId");

        Member member = memberService.getMember(userId).orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));


        List roles = (List) claims.get("roles");
        String email = claims.getSubject();

        String accessToken = jwtTokenizer.createAccessToken(userId, email, roles);

        MemberLoginResponseDTO loginResponse = MemberLoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshTokenDTO.getRefreshToken())
                .memberId(member.getMemberId())
                .memberName(member.getMemberName())
                .build();
        return new ResponseEntity(loginResponse, HttpStatus.OK);
    }

}
