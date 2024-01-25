package com.example.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.InquiryDAO;
import com.example.domain.Inquiry;
import com.example.domain.ServerResponse;
import com.example.service.FilesService;
import com.example.service.InquiryService;
import com.example.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService{

	private final FilesService filesService;
	private final ValidationService validationService;
	private final InquiryDAO inquiryDAO;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-25
     *  @param		: Inquiry inquiry, List<MultipartFile> fileList 
     *  @return		: ResponseEntity
     * 	@explain	: 문의 등록
     * */
	@Transactional
	public ResponseEntity<?> registInquiry(Inquiry inquiry, List<MultipartFile> fileList) {
		ServerResponse response = new ServerResponse();
		List<String[]> checkValues = new ArrayList<>();
		
		String inquiryType = inquiry.getInquiryType();
		String inquiryTitle = inquiry.getInquiryTitle();
		String inquiryComments = inquiry.getInquiryComments();
		
		checkValues.add(new String[]{"", "문의유형", inquiryType});
		checkValues.add(new String[]{"", "문의제목", inquiryTitle});
		checkValues.add(new String[]{"", "문의내용", inquiryComments});
		
		ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
		
        if (validationResponse != null) {
            return validationResponse;
        }
        
        // 파일 존재 시 파일 등록
        if(fileList != null) {
        	String fileKey = filesService.fileupload(fileList, inquiry.getInquiryWriterEmail());
        	inquiry.setFileKey(fileKey);
        }
        
        // 문의 등록
        inquiryDAO.registInquiry(inquiry);
        
        response.setSuccess(true);
        response.setMessage(inquiry.getInquiryWriterEmail() + "님, 문의등록이 완료되었습니다.");
        
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
