package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Member;

@Mapper
public interface PaymentDAO {
	// 회원 결제정보 조회
	List<Member> getUserPaymentInfo(Integer memberId);

}
