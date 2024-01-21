package com.example.service;

import java.util.List;

import com.example.domain.Drugs;
import com.example.dto.DrugsDTO;

public interface DrugsService {

	/**
	 *	****************MyBatis********************* 
	 * */
	List<Drugs> getDrugsList(DrugsDTO dto);
	
	
	/**
	 *	*******************JPA*********************** 
	 * */

}
