package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Hospital;

@Mapper
public interface HospitalDAO {

    List<Hospital> getHospitalList();

	List<Hospital> getHospitalListByKeyword(String keyword);

	List<Hospital> getCurrentLocationList(Map<String, String> requestData);
}
