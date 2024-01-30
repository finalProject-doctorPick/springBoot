package com.example.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DoctorDAO;
import com.example.dao.ReservationDAO;
import com.example.domain.Doctor;
import com.example.domain.DoctorAvail;
import com.example.domain.MemberHistory;
import com.example.domain.Reservation;
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
	private final ReservationDAO reservationDAO;
	
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
     *  @param		: Integer reservationNum(진료id)
     *  @return		: MemberHistory 
     * 	@explain	: 특정 진료 상세보기 조회
     * */
	@Override
	public MemberHistory getDetailedHistory(Integer certificateNum) {
		return doctorDAO.getDetailedHistory(certificateNum);
	}
	
	/*
     * 	@author 	: 박병태
     *  @created	: 2024-01-23
     *  @param		: Integer doctorId 
     *  @return		: String doctorEmail 
     * 	@explain	: 특정 진료 상세보기 조회
     * */
	@Override
	public String getDoctorEmailFromId(Integer doctorId) {
		return doctorDAO.getDoctorEmail(doctorId);
	}

	
	/**

	 * 	@author     : 이성규
	 *  @created    : 2024-01-26
	 *  @param      : String doctorMajor
	 *  @return     : List<Doctor>
	 * 	@explain    : 진료) 의사 목록 조회 - 진료과목
	 * */
	
	@Override
	public List<?> getDoctorClinicList(String doctorSubject) {
		return doctorDAO.getDoctorClinicList(doctorSubject);
	}
	
	/**
	 * 	@author     : 이성규
	 *  @created    : 2024-01-27
	 *  @param      : Integer doctorId
	 *  @return     : List<Doctor>
	 * 	@explain    : 진료) 의사 상세 - 리뷰
	 * */

	@Override
	public List<?> getDoctorReview(Integer doctorId) {
		return doctorDAO.getDoctorReview(doctorId);
	}

	/**
     * 	@author 	: 백두산
     *  @created	: 2024-01-29
     *  @param		: Integer doctorId
     *  @return		: List<?> list
     * 	@explain	: 의사 비대면진료 목록 조회 (접수대기/진료목록/진료종료) 
     * */
	@Transactional
	public Map<String, List<?>> getDoctorNonFaceToFaceList(Integer doctorId) {
		Map<String, List<?>> result = new HashMap<>();
		System.out.println("getDoctorNonFaceToFaceList 진입");
		// 접수대기 목록 조회
		List<Reservation> reservationWaitList = reservationDAO.getDoctorReservationWaitList(doctorId);
		System.out.println("접수대기 목록 조회 후 list : " + reservationWaitList.size());
		
		// 진료대기 목록 조회
		List<Reservation> reservationConfirmList = reservationDAO.getDoctorReservationConfirmList(doctorId);
		System.out.println("진료대기 목록 조회 후 list : " + reservationConfirmList.size());
		
		// 진료종료 목록 조회
		List<Reservation> reservationFinishList = reservationDAO.getDoctorReservationFinishList(doctorId);
		System.out.println("진료종료 목록 조회 후 list : " + reservationFinishList.size());
		
		result.put("waitList", reservationWaitList);
		result.put("confirmList", reservationConfirmList);
		result.put("finishList", reservationFinishList);
		
		return result;
	}
	
	/**
     * 	@author 	: 정하림
     *  @created	: 2024-01-29
     *  @param		: String doctorEmail
     *  @return		: List<DoctorAvail>
     * 	@explain	: 의사정보 조회(진료시간)
     * */
	@Override
	public List<DoctorAvail> getDoctorAvailList(String doctorEmail) {
		
		return doctorDAO.getDoctorAvailList(doctorEmail);
	}

	
	/**
     * 	@author 	: 정하림
     *  @created	: 2024-01-29
     *  @param		: String doctorEmail
     *  @return		: Doctor
     * 	@explain	: 의사정보 조회
     * */
	@Override
	public Doctor getDoctorInfoList(String doctorEmail) {
		
		return doctorDAO.getDoctorInfoList(doctorEmail);
	}

}
