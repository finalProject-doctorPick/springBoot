package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-01
     *  @param		: Integer reservationNum
     *  @return		: List<MemberHistory>
     * 	@explain	: 진료 등록
     * */
	@Transactional
	public void registCertificate(Integer reservationNum) {
		certificateDAO.registCertificate(reservationNum);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-01
     *  @param		: Integer certificateNum, Integer memberId
     *  @return		: ResponseEntity
     * 	@explain	: 환자 진료 취소
     * */
	@Transactional
	public void cancelCertification(Integer certificateNum) {
		certificateDAO.cancelCertification(certificateNum);
	}

}
