package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.DrugstoreEntity;

public interface DrugstoreRepository extends JpaRepository<DrugstoreEntity, Integer>{
	boolean existsByDrugstoreEmail(String email);
	
	DrugstoreEntity findByDrugstoreEmail(String email);
}
