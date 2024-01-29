package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.domain.Hospital;

public interface HospitalService {

	/**
	 *	****************MyBatis********************* 
	 * */
	public List<Hospital> getHospitalList();

	public List<Hospital> getHospitalListByKeyword(String keyword);

	public List<Hospital> getCurrentLocationList(Map<String, String> requestData);

	public List<Hospital> getHospitalRegionCnt();

}
