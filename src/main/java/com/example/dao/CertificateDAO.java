package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Certificate;

@Mapper
public interface CertificateDAO {

	// 환자 진료내역 조회
	List<Certificate> getMemberCertificateHistory(Integer memberId);

}
