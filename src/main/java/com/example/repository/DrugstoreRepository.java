package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.Drugstore;

public interface DrugstoreRepository extends JpaRepository<Drugstore, Integer>{
	boolean existsByDrugstoreEmail(String email);
}
