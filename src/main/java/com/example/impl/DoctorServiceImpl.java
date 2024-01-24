package com.example.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DoctorDAO;
import com.example.domain.Doctor;
import com.example.domain.Member;
import com.example.domain.MemberHistory;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.DoctorEntity;
import com.example.entity.RoleEntity;
import com.example.repository.DoctorRepository;
import com.example.repository.RoleRepository;
import com.example.service.DoctorService;
import com.example.service.FilesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService{
	
	private final DoctorDAO doctorDAO;
	private final DoctorRepository doctorRepository;
	private final RoleRepository roleRepository;
	private final FilesService filesService;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-12
     *  @param		: String email
     *  @return		: Boolean
     * 	@explain	: 의사 이메일 존재 확인
     * */
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
	public ServerResponse registerDoctor(Users doctorData, List<MultipartFile> fileList) {
		DoctorEntity insertDoctor = modelMapper.map(doctorData, DoctorEntity.class);
		ServerResponse userSignupResponseDTO = new ServerResponse();

        // 의사 역할 부여
        String role = "ROLE_DOCTOR";
        
        RoleEntity doctorRole = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found"));
        
		// 의사 파일 등록
		String fileKey = filesService.fileupload(fileList, doctorData.getUserEmail());
		
		// 의사 정보 설정
		insertDoctor.addRole(doctorRole);
		insertDoctor.setDoctorEmail(doctorData.getUserEmail());
		insertDoctor.setDoctorPwd(passwordEncoder.encode(doctorData.getUserPwd()));
		insertDoctor.setDoctorName(doctorData.getUserName());
		insertDoctor.setDoctorName(doctorData.getUserName());
		insertDoctor.setDoctorBirth(doctorData.getUserBirth());
		insertDoctor.setDoctorSex(doctorData.getUserSex());
		insertDoctor.setDoctorAddrMain(doctorData.getUserAddrMain());
		insertDoctor.setDoctorAddrDetail(doctorData.getUserAddrDetail());
		insertDoctor.setDoctorConfirmYn("N");
		insertDoctor.setFileKey(fileKey);
		insertDoctor.setDoctorTel(doctorData.getUserTel().replaceAll("-", ""));
		
		// 의사 정보 저장
		DoctorEntity result = doctorRepository.save(insertDoctor);
		
		userSignupResponseDTO.setSuccess(true);
        userSignupResponseDTO.setMessage("DOCTORPICK 의사가입이 완료되었습니다. \n관리자 확인 후 이용 가능 합니다.");
        userSignupResponseDTO.setUserAuth("D");
        userSignupResponseDTO.setUserId(result.getDoctorId());
        userSignupResponseDTO.setUserName(result.getDoctorName());
        
		return userSignupResponseDTO;
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-15
     *  @param		: String email, String pwd
     *  @return		: Doctor
     * 	@explain	: 의사 회원 로그인
     * */
	@Transactional(readOnly = true)
	public Doctor validateDoctorEmailAndPwd(String email, String pwd) {
		Doctor d = doctorDAO.findDoctorByEmail(email);
		
		// 비밀번호 체크
    	if(d != null){
    		return (passwordEncoder.matches(pwd, d.getDoctorPwd())) ? d : null;
    	}else {
    		return null;
    	}
	}
	
	@Transactional(readOnly = true)
	public DoctorEntity getDoctor(String email) {
		return doctorRepository.findByDoctorEmail(email);
	}

	
	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-22
     *  @param		: doctorId(의사 id번호)
     *  @return		: List(Generic)
     * 	@explain	: 해당 의사의 진료 불러오기
     * */
	@Override
	public List<?> getDoctorCurrentHistory(Integer doctorId) {
		return doctorDAO.getDoctorCurrentHistory(doctorId);
	}

	/**
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: reservationNum(진료id)
     *  @return		: MemberHistory 
     * 	@explain	: 특정 진료 상세보기 조회
     * */
	@Override
	public MemberHistory getDetailedHistory(Integer certificateNum) {
		return doctorDAO.getDetailedHistory(certificateNum);
	}

	@Override
	public int getDoctorRequestCnt() {
		return doctorDAO.getDoctorRequestCnt();
	}

	@Override
	public List<Doctor> getDoctorsList() {
		return doctorDAO.getDoctorsList();
	}

	@Override
	public List<Doctor> getRegistRequestList() {
		return doctorDAO.getRegistRequestList();
	}

}
