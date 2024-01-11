package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DrugstoreDAO;
import com.example.dto.UserSignupDTO;
import com.example.repository.DrugstoreRepository;
import com.example.service.DrugstoreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrugstoreServiceImpl implements DrugstoreService {

	private final DrugstoreRepository drugstoreRepository;
	private final DrugstoreDAO drugstoreDAO;
	
	public boolean existsByDrugstoreEmail(String email) {
		return drugstoreRepository.existsByDrugstoreEmail(email);
	}

	@Override
	public Object registerDrugstore(UserSignupDTO userSignupDTO, List<MultipartFile> fileList) {
		// TODO Auto-generated method stub
		return null;
	}

}
