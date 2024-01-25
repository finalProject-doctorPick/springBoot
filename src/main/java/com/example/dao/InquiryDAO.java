package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Inquiry;

@Mapper
public interface InquiryDAO {

	// 문의 등록
	void registInquiry(Inquiry inquiry);
}
