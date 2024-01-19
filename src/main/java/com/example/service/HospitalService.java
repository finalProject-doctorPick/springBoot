package com.example.service;

import java.util.List;

import com.example.domain.Hospital;

public interface HospitalService {

	List<Hospital> getHospitalList();

	List<Hospital> getHospitalListByKeyword(String keyword);

}
