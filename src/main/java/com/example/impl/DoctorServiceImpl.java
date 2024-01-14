package com.example.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DoctorDAO;
import com.example.domain.Doctor;
import com.example.domain.Role;
import com.example.domain.UserRequest;
import com.example.domain.UserResponse;
import com.example.dto.DoctorDTO;
import com.example.repository.DoctorRepository;
import com.example.repository.RoleRepository;
import com.example.service.DoctorService;
import com.example.service.FilesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
	
	private final DoctorRepository doctorRepository;
	private final RoleRepository roleRepository;
	private final DoctorDAO doctorDAO;
	private final FilesService filesService;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = true)
	public boolean existsByDoctorEmail(String email) {
		return doctorRepository.existsByDoctorEmail(email);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-12
     *  @param		: UserSignupDTO userSignupDTO, List<MultipartFile> fileList
     *  @return		: ResponseEntity
     * 	@explain	: 의사 회원 등록
     * */
	@Transactional
	public UserResponse registerDoctor(UserRequest userSignupDTO, List<MultipartFile> fileList) {
		DoctorDTO insertDoctor = new DoctorDTO();
		UserResponse userSignupResponseDTO = new UserResponse();

		System.out.println("**************************************");
        System.out.println("***********의사 회원가입 진행************");
        System.out.println("**************************************");
        
        // 의사 역할 부여
        String role = "ROLE_DOCTOR";
        Role doctorRole = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found"));
        
		// 의사 파일 등록
		String fileKey = filesService.fileupload(fileList, userSignupDTO.getUserEmail());
		
		// 의사 정보 설정
		insertDoctor.addRole(doctorRole);
		insertDoctor.setDoctorEmail(userSignupDTO.getUserEmail());
		insertDoctor.setDoctorPwd(passwordEncoder.encode(userSignupDTO.getUserPwd()));
		insertDoctor.setDoctorName(userSignupDTO.getUserName());
		insertDoctor.setDoctorName(userSignupDTO.getUserName());
		insertDoctor.setDoctorBirth(userSignupDTO.getUserBirth());
		insertDoctor.setDoctorSex(userSignupDTO.getUserSex());
		insertDoctor.setDoctorAddrMain(userSignupDTO.getUserAddrMain());
		insertDoctor.setDoctorAddrDetail(userSignupDTO.getUserAddrDetail());
		insertDoctor.setFileKey(fileKey);
		
		// 의사 정보 저장
		doctorDAO.registerDoctor(insertDoctor);
		
		Doctor registeredDoctor = doctorDAO.findByDoctorByEmail(insertDoctor.getDoctorEmail());
		
		userSignupResponseDTO.setSuccess(true);
        userSignupResponseDTO.setMessage("DOCTORPICK 의사가입이 완료되었습니다. \n관리자 확인 후 이용 가능 합니다.");
        userSignupResponseDTO.setUserAuth("D");
        userSignupResponseDTO.setUserId(registeredDoctor.getDoctorId());
        userSignupResponseDTO.setUserName(registeredDoctor.getDoctorName());
        
		return userSignupResponseDTO;
	}

	@Transactional(readOnly = true)
	public Doctor findByDoctorEmail(String email, String pwd) {
		Doctor d = doctorDAO.findByDoctorByEmail(email);
		
		return null;
	}

}
