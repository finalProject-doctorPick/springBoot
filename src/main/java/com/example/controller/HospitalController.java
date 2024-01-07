package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Hospital;
import com.example.domain.Member;
import com.example.security.jwt.util.IfLogin;
import com.example.security.jwt.util.LoginUserDto;
import com.example.service.HospitalService;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

	private final MemberService memberService;
	private final HospitalService hospitalService;
	
	@GetMapping("/getHospitalList")
	public List<Hospital> getHospitalList(@IfLogin LoginUserDto loginUserDto){
		Optional<Member> member = memberService.getMember(loginUserDto.getEmail());
		System.out.println("************************************");
		System.out.println("member 값 : " + member.toString());
		System.out.println("************************************");
		if(member.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자가 없습니다.");
        }
		
		List<Hospital> list = hospitalService.getHospitalList();
		
		return list;
	}
}
