package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Drugstore;
import com.example.service.DrugstoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drugstores")
public class DrugstoreController {
	
	private final DrugstoreService drugstoreService;
	
	@GetMapping("/getDrugstoreList")
	public ResponseEntity<?> getDrugstoreList(){
		List<Drugstore> list = drugstoreService.getDrugstoreList();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
