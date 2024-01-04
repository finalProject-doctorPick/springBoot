package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SignupDAO;
import com.example.domain.SignupDTO;

@Service
public class SignupServiceImpl implements SignupService{

	@Autowired
	SignupDAO signupDAO;
	
	@Override
	public void regist(SignupDTO signupDTO) {
		signupDAO.regist(signupDTO);
	}

}
