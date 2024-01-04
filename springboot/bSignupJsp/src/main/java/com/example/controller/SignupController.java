package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.SignupDTO;
import com.example.service.SignupService;

@Controller
@RequestMapping("signup")
public class SignupController {
	
			
	@Autowired
	SignupService signupService;
	
	@RequestMapping("/registForm")
	public String registForm() {
		return "registForm";
	}
	
	@RequestMapping("/register")
	public String regist(@ModelAttribute("signupDTO") SignupDTO signupDTO) {
		System.out.println(signupDTO);
		signupService.regist(signupDTO);
		return "regist";
	}

}
