package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Hospital;
import com.example.security.jwt.util.JwtTokenizer;
import com.example.service.HospitalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HospitalController {

	private HospitalService hospitalService;
	
	@GetMapping("/api/hospitalList")
	public List<Hospital> getHospitalList(){
		List<Hospital> list = hospitalService.getHospitalList();
		
		return list;
	}
}
