package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.ReservationEntity;

public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer>{
	List<ReservationEntity> findByDoctorId(Integer doctorId);
}
