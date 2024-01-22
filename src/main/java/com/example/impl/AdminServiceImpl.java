package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.AdminDAO;
import com.example.domain.Inquiry;
import com.example.domain.Member;
import com.example.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	private final AdminDAO adminDAO;
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-19
     *  @param		: String searchKeyword
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 회원 목록 조회
     * */
	@Transactional(readOnly = true)
	public List<?> getMemberList(String searchKeyword) {
		List<Member> response = adminDAO.getMemberList(searchKeyword);
		return response;
	}
	
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-22
     *  @param		: void
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 문의 목록 조회
     * */
	@Transactional(readOnly = true)
	public List<?> getInquiryList(String userEmail) {
		List<Inquiry> response = adminDAO.getInquiryList(userEmail);
		return response;
	}
}
