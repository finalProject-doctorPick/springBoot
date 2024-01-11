package com.example.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DoctorDAO;
import com.example.domain.Doctor;
import com.example.dto.DoctorSignupDTO;
import com.example.dto.UserSignupDTO;
import com.example.repository.DoctorRepository;
import com.example.service.DoctorService;
import com.example.service.FilesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
	
	private final DoctorRepository doctorRepository;
	private final DoctorDAO doctorDAO;
	private final FilesService filesService;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public boolean existsByDoctorEmail(String email) {
		return doctorRepository.existsByDoctorEmail(email);
	}

	@Transactional
	public Object registerDoctor(UserSignupDTO userSignupDTO, List<MultipartFile> fileList) {
		DoctorSignupDTO dto = new DoctorSignupDTO();

		System.out.println("**************************************");
        System.out.println("***********의사 회원가입 진행************");
        System.out.println("**************************************");
        
		// 의사 파일 등록
		String fileKey = filesService.fileupload(fileList, userSignupDTO.getUserEmail());
		
		dto.setDoctorEmail(userSignupDTO.getUserEmail());
		dto.setDoctorPwd(passwordEncoder.encode(userSignupDTO.getUserPwd()));
		dto.setDoctorName(userSignupDTO.getUserName());
		dto.setDoctorName(userSignupDTO.getUserName());
		dto.setDoctorBirth(userSignupDTO.getUserBirth());
		dto.setDoctorSex(userSignupDTO.getUserSex());
		dto.setDoctorAddrMain(userSignupDTO.getUserAddrMain());
		dto.setDoctorAddrDetail(userSignupDTO.getUserAddrDetail());
		dto.setFileKey(fileKey);
		
		Doctor result = doctorDAO.registerDoctor(dto);
		result.toString();
		return null;
	}

}
