package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Hospital;
import com.example.service.HospitalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

	private final HospitalService hospitalService;
	
	@GetMapping("/getHospitalList")
	public ResponseEntity<?> getHospitalList(){
		List<Hospital> list = hospitalService.getHospitalList();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
}
