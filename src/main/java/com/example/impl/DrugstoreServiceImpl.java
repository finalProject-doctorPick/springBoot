package com.example.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DrugstoreDAO;
import com.example.domain.Drugstore;
import com.example.domain.UserRequest;
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
	public Object registerDrugstore(UserRequest userSignupDTO, List<MultipartFile> fileList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drugstore findByDrugstoreEmail(String email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

}
