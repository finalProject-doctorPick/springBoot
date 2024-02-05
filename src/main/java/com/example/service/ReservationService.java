package com.example.service;

import java.util.List;

import com.example.domain.Reservation;

public interface ReservationService {

	// 접수대기 목록 조회
	List<Reservation> getDoctorReservationWaitList(Integer doctorId);

	// 진료대기 목록 조회
	List<Reservation> getDoctorReservationConfirmList(Integer doctorId);

	// 진료종료 목록 조회
	List<Reservation> getDoctorReservationFinishList(Integer doctorId);

	// 예약 정보 수정
	void updateReservationStatus(Integer reservationNum);

	// 예약 취소
	void cancelReservation(Integer reservationNum);

	// 예약정보 조회
	Reservation getReservationDataForCertificateNum(Integer certificateNum);
	
}
