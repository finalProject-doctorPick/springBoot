package com.example.service;

import java.util.List;

import com.example.domain.Certificate;

public interface CertificateService {

	// 환자 진료내역 조회
	public List<Certificate> getMemberCertificateHistory(Integer memberId);

	// 진료 등록
	public void registCertificate(Integer reservationNum);
	
}
