package com.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.HospitalDAO;
import com.example.domain.Hospital;
import com.example.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService{
	
	private final HospitalDAO hospitalDAO = null;

	@Override
	public List<Hospital> getHospitalList() {
		List<Hospital> list = hospitalDAO.getHospitalList();
		System.out.println("데이터 확인 : " + list.size());
		
		return list;
	}
	
	
	
}
