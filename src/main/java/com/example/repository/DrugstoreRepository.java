package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dto.DrugstoreDTO;

public interface DrugstoreRepository extends JpaRepository<DrugstoreDTO, Integer>{
	boolean existsByDrugstoreEmail(String email);
}
