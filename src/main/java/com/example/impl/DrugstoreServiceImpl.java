package com.example.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.DrugstoreDAO;
import com.example.dao.InquiryDAO;
import com.example.domain.Drugstore;
import com.example.domain.DrugstoreHistory;
import com.example.domain.Inquiry;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.DrugstoreEntity;
import com.example.entity.RoleEntity;
import com.example.repository.DrugstoreRepository;
import com.example.repository.RoleRepository;
import com.example.service.DrugstoreService;
import com.example.service.FilesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DrugstoreServiceImpl implements DrugstoreService {

	private final DrugstoreRepository drugstoreRepository;
	private final DrugstoreDAO drugstoreDAO;
	private final RoleRepository roleRepository;
	private final FilesService filesService;
	private final PasswordEncoder passwordEncoder;
	private final InquiryDAO inquiryDAO;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-15
     *  @param		: String email
     *  @return		: Boolean
     * 	@explain	: 약국 이메일 존재 확인
     * */
	@Transactional(readOnly = true)
	public boolean existsByDrugstoreEmail(String email) {
		return drugstoreRepository.existsByDrugstoreEmail(email);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-15
     *  @param		: UserRequest userSignupDTO, List<MultipartFile> fileList
     *  @return		: Boolean
     * 	@explain	: 약국 회원 등록
     * */
	@Transactional
	public ServerResponse registerDrugstore(Users drugstoreData, List<MultipartFile> fileList) {
		DrugstoreEntity insertDrugstore = new DrugstoreEntity();
		ServerResponse response = new ServerResponse();
		
		// 약국 역할 부여
		String role = "ROLE_DRUGSTORE";
		
		RoleEntity drugstoreRole = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found"));
		
		// 약국 파일 등록
		String fileKey = filesService.fileupload(fileList, drugstoreData.getUserEmail());
		
		// 약국 정보 설정
		insertDrugstore.addRole(drugstoreRole);
		insertDrugstore.setDrugstoreEmail(drugstoreData.getUserEmail());
		insertDrugstore.setDrugstorePwd(passwordEncoder.encode(drugstoreData.getUserPwd()));
		insertDrugstore.setDrugstoreName(drugstoreData.getUserName());
		insertDrugstore.setDrugstoreName(drugstoreData.getUserName());
		insertDrugstore.setDrugstoreBirth(drugstoreData.getUserBirth());
		insertDrugstore.setDrugstoreSex(drugstoreData.getUserSex());
		insertDrugstore.setDrugstoreAddrMain(drugstoreData.getUserAddrMain());
		insertDrugstore.setDrugstoreAddrDetail(drugstoreData.getUserAddrDetail());
		insertDrugstore.setFileKey(fileKey);
		insertDrugstore.setDrugstoreTel(drugstoreData.getUserTel().replaceAll("-", ""));
		insertDrugstore.setDrugstoreConfirmYn("N");
		
		// 약국 정보 저장
		DrugstoreEntity result = drugstoreRepository.save(insertDrugstore);
		
		response.setSuccess(true);
		response.setMessage("DOCTORPICK 약국가입이 완료되었습니다. \n관리자 확인 후 이용 가능 합니다.");
		response.setUserAuth("S");
		response.setUserId(result.getDrugstoreId());
		response.setUserName(result.getDrugstoreName());
		
		return response;
	}

	@Transactional(readOnly = true)
	public Drugstore validateDrugstoreEmail(String email, String pwd) {
		Drugstore s = drugstoreDAO.validateDrugstoreEmail(email);
		// 비밀번호 체크
    	if(s != null){
    		return (passwordEncoder.matches(pwd, s.getDrugstorePwd())) ? s : null;
    	}else {
    		return null;
    	}
	}

	@Transactional(readOnly = true)
	public DrugstoreEntity getDrugstore(String email) {
		return drugstoreRepository.findByDrugstoreEmail(email);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-17
     *  @param		: void
     *  @return		: List<Drugstore>
     * 	@explain	: 약국 리스트 조회
     * */
	@Transactional(readOnly = true)
	public List<Drugstore> getDrugstoreList() {
		return drugstoreDAO.getDrugstoreList();
	}
	
	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-19
     *  @param		: String keyword
     *  @return		: ResponseEntity
     * 	@explain	: 검색어로 병원 검색
     * 
     * */
	@Override
	public List<Drugstore> getDrugstoreListByKeyword(String keyword) {
		return drugstoreDAO.getDrugstoreListByKeyword(keyword);
	}
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-21
     *  @param		: Integer drugstoreId
     *  @return		: List<DrugstoreHistory>
     * 	@explain	: 약국 히스토리 조회
     * */
	@Transactional(readOnly = true)
	public List<DrugstoreHistory> getDrugstoreHistoryList(Integer drugstoreId) {
		return drugstoreDAO.getDrugstoreHistoryList(drugstoreId);
	}

	/*
     * 	@author 	: 이성규	 
     *  @created	: 2024-01-24
     *  @param		: Integer drugstoreId
     *  @return		: List<Inquiry>
     * 	@explain	: 약국) 문의 목록 조회
     * */
	@Transactional(readOnly = true)
	public List<Inquiry> getDrugstoreInquiry(Integer drugstoreId) {
		return inquiryDAO.getDrugstoreInquiryList(drugstoreId);
	}

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-26
     *  @param		: Drugstore drugstoreData
     *  @return		: ResponseEntity
     * 	@explain	: 약국 정보 수정
     * */	
	@Transactional
	public ResponseEntity<?> updateDrugstoreInfo(Drugstore storeData) {
		return null;
	}
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-02-01
     *  @param		: DrugstoreHistory storeHistory
     *  @return		: ResponseEntity
     * 	@explain	: 약국 수령확인 
     * */
	@Transactional
	public ResponseEntity<?> updateDrugstoreHistory(DrugstoreHistory storeHistory) {
		ServerResponse response = new ServerResponse();

		int result = drugstoreDAO.updateDrugstoreHistory(storeHistory);
		
	    if (result > 0) {
	        // 업데이트 성공
	        response.setSuccess(true);
	        response.setMessage("수령 확인이 완료되었습니다.");
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        // 업데이트 실패
	        response.setSuccess(false);
	        response.setMessage("등록에 실패했습니다.");
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

		
	}

	@Override
	public Integer newOrder(Integer drugstoreId) {
		return drugstoreDAO.newOrder(drugstoreId);
	}

	@Override
	public Integer receiveWait(Integer drugstoreId) {
		return drugstoreDAO.receiveWait(drugstoreId);
	}

	@Override
	public Integer received(Integer drugstoreId) {
		return drugstoreDAO.received(drugstoreId);
	}

}
