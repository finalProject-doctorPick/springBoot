package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Reservation;

@Mapper
public interface ReservationDAO {
	
	// 의사) 접수대기 목록 조회
	List<Reservation> getDoctorReservationWaitList(Integer doctorId);
	
	// 의사) 진료대기 목록 조회
	List<Reservation> getDoctorReservationConfirmList(Integer doctorId);

	// 의사) 진료종료 목록 조회
	List<Reservation> getDoctorReservationFinishList(Integer doctorId);
}
