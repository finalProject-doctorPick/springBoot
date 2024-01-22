package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Hospital;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.service.DoctorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {
	
	private final DoctorService doctorService;
	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-22
     *  @param		: doctorId(의사 id번호)
     *  @return		: ResponseEntity
     * 	@explain	: 해당 의사의 진료 불러오기
     * */
	@GetMapping("/getDoctorCurrentHistory")
	public ResponseEntity<?> getDoctorCurrentHistory(@RequestParam Integer doctorId){
		System.out.println(doctorId);
    	List<?> list = doctorService.getDoctorCurrentHistory(doctorId);
    	System.out.println("getDoctorCurrentHistory: "+list.toString());
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }
	

}
