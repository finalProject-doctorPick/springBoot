package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.DrugsDTO;
import com.example.service.DrugsService;


@CrossOrigin
@RestController
@RequestMapping("/drugs")
public class DrugsController {

	@Autowired
    private DrugsService drugsService;

	
	

}
