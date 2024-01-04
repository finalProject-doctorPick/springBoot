package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Employee;
import com.example.persistence.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{


	@Autowired
	EmployeeRepository repo;
	
	@Override
	public List<Object[]> getEmployeeList() {
		return repo.getEmployeeList();
	}

}
