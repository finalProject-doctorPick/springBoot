package com.example.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.SignupDTO;

@Mapper
public interface SignupDAO {

	public void regist(SignupDTO signupDTO);

}
