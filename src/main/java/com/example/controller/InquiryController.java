package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Inquiry;
import com.example.service.InquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inquiry")
public class InquiryController {
	
	private final InquiryService inquiryService;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-25
     *  @param		: Inquiry inquiry, List<MultipartFile> fileList 
     *  @return		: ResponseEntity
     * 	@explain	: 문의 등록
     * */
	@PostMapping("/registInquiry")
	public ResponseEntity<?> registInquiry(@ModelAttribute Inquiry inquiry, @RequestPart(name = "fileList", required = false) List<MultipartFile> fileList){
		
		System.out.println("**********************************");
		System.out.println("/registInquiry 진입");
		System.out.println("inquiry 값 : " + inquiry.toString());
		System.out.println("fileList size : " + fileList.size());
		ResponseEntity<?> responseEntity = inquiryService.registInquiry(inquiry, fileList);
		return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
	}
}
