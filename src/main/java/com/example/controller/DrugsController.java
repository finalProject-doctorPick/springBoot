package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Drugs;
import com.example.dto.DrugsDTO;
import com.example.service.DrugsService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class DrugsController {

	@Autowired
    private DrugsService drugsService;

	@GetMapping("/drugsSearch")
	public List<Drugs> getDrugsList(@RequestParam String drugText, @RequestParam String drugColor){
		DrugsDTO dto = new DrugsDTO();
		dto.setDrugText(drugText);
	    dto.setDrugFront(drugText);
	    dto.setDrugBack(drugText);
		dto.setDrugColor(drugColor);
		
		System.out.println("=====================> dto: " + dto);
			
		return drugsService.getDrugsList(dto);
	}
	 
}
