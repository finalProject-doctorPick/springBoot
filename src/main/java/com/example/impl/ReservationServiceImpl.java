package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ReservationDAO;
import com.example.domain.Reservation;
import com.example.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{
	
	private final ReservationDAO reservationDAO;
	
	@Transactional(readOnly = true)
	public List<Reservation> getDoctorReservationWaitList(Integer doctorId) {
		return reservationDAO.getDoctorReservationWaitList(doctorId);
	}

	@Transactional(readOnly = true)
	public List<Reservation> getDoctorReservationConfirmList(Integer doctorId) {
		return reservationDAO.getDoctorReservationConfirmList(doctorId);
	}

	@Transactional(readOnly = true)
	public List<Reservation> getDoctorReservationFinishList(Integer doctorId) {
		return reservationDAO.getDoctorReservationFinishList(doctorId);
	}

	@Transactional
	public void updateReservationStatus(Integer reservationNum) {
		reservationDAO.updateReservationStatus(reservationNum);
	}

	@Override
	public void cancelReservation(Integer reservationNum) {
		reservationDAO.cancelReservation(reservationNum);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-05
     *  @param		: Integer certificateNum
     *  @return		: Reservation
     * 	@explain	: 진료정보 조회
     * */
	@Transactional
	public Reservation getReservationDataForCertificateNum(Integer certificateNum) {
		return reservationDAO.getReservationDataForCertificateNum(certificateNum);
	}

}
