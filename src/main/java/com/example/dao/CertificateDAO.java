package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Certificate;

@Mapper
public interface CertificateDAO {

	// 환자 진료내역 조회
	List<Certificate> getMemberCertificateHistory(Integer memberId);

	Integer getCertificateCnt();

	// 진료 등록
	void registCertificate(Integer reservationNum);

	// 진료 취소
	void cancelCertification(Integer certificateNum);

	// 진료실 입장
	void updateCertificateStaus(Integer certificateNum);
	
	// 진료대기실 입장
	List<Certificate> getCertificateInfo(Integer certificateNum);

	// 진료 완료
	void finishCertificate(Certificate certificateData);

	// 결제완료 처리
	void finishPayments(Integer certificateNum);

}
