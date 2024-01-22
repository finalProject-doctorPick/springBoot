package com.example.service;

import java.util.List;
import java.util.Map;

import com.example.domain.Hospital;

public interface HospitalService {

	List<Hospital> getHospitalList();

	List<Hospital> getHospitalListByKeyword(String keyword);

	List<Hospital> getCurrentLocationList(Map<String, String> requestData);

}
