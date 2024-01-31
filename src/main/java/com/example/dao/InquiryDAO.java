package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Inquiry;

@Mapper
public interface InquiryDAO {

	// 문의 등록
	void registInquiry(Inquiry inquiry);
	
	// 관리자) 문의 답변
	int updateInquiryAnswer(Inquiry inquiryData);

	// 약국) 문의 조회
	List<Inquiry> getDrugstoreInquiryList(Integer drugstoreId);

	// 의사) 문의 조회
	List<Inquiry> getDoctorInquiryList(Integer doctorId);

}
