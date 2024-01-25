package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Inquiry;

public interface InquiryService {

	// 문의 등록
	ResponseEntity<?> registInquiry(Inquiry inquiry, List<MultipartFile> fileList);

}
