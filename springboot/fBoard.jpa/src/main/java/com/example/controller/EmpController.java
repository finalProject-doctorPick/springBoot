package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.EmployeeService;

@Controller
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	EmployeeService empService;
	
	@RequestMapping("/getEmployeeList")
    public void getEmployeeList(Model m) {
		List<Object[]> result = empService.getEmployeeList();
        m.addAttribute("emp", result);
    }
	
	
}
