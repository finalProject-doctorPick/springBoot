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

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/members")
public class MemberController {

	private final MemberService memberService;

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-18
     *  @param		: Users userData
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 최근 진료 내역
     * */
    @GetMapping("/currentHistory")
    public ResponseEntity<?> getMemberCurrentHistory(@RequestParam Integer memberId){
    	
    	List<?> list = memberService.getMemberCurrentHistory(memberId);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
}