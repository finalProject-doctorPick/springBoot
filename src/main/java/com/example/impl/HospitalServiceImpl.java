package com.example.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.HospitalDAO;
import com.example.domain.Hospital;
import com.example.service.HospitalService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

    private final HospitalDAO hospitalDAO;

    @Transactional(readOnly = true)
    public List<Hospital> getHospitalList() {
        return hospitalDAO.getHospitalList();
    }

	@Override
	public List<Hospital> getHospitalListByKeyword(String keyword) {

		return hospitalDAO.getHospitalListByKeyword(keyword);
	}

	@Override
	public List<Hospital> getCurrentLocationList(Map<String, String> requestData) {

		return hospitalDAO.getCurrentLocationList(requestData);
	}
}
