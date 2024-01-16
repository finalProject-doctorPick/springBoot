package com.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DrugsDAO;
import com.example.dto.DrugsDTO;
import com.example.service.DrugsService;


@Service
public class DrugsServiceImpl implements DrugsService {
	
	@Autowired
	private DrugsDAO drugsDAO;

	@Override
	public List<DrugsDTO> getDrugsList() {
        List<DrugsDTO> drugsList = drugsDAO.getDrugsList();

        return drugsList;
	}
	

}
