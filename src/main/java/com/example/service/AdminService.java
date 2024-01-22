package com.example.service;

import java.util.List;

public interface AdminService {

	// 관리자) 회원 조회
	List<?> getMemberList(String searchKeyword);

	// 관리자) 문의 조회
	List<?> getInquiryList(String userEmail);

}
