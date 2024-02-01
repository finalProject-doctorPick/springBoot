package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.CertificateDAO;
import com.example.domain.Certificate;
import com.example.service.CertificateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService{
	
	private final CertificateDAO certificateDAO;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-31
     *  @param		: Integer memberId
     *  @return		: List<Certificate> list
     * 	@explain	: 환자 진료내역 조회
     * */	
	public List<Certificate> getMemberCertificateHistory(Integer memberId) {
		return certificateDAO.getMemberCertificateHistory(memberId);
	}

}
