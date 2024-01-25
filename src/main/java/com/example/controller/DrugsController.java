package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Drugs;
import com.example.dto.DrugsDTO;
import com.example.service.DrugsService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DrugsController {

    private final DrugsService drugsService;

	@GetMapping("/drugsSearch")
	public ResponseEntity<?> getDrugsList(@RequestParam String drugText, @RequestParam String drugColor){
		DrugsDTO dto = new DrugsDTO();
		dto.setDrugText(drugText);
	    dto.setDrugFront(drugText);
	    dto.setDrugBack(drugText);
		dto.setDrugColor(drugColor);
		
		List<Drugs> list = drugsService.getDrugsList(dto);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	 
}
