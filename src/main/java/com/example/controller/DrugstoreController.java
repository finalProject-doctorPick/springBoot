package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Drugstore;
import com.example.domain.DrugstoreHistory;
import com.example.domain.Inquiry;
import com.example.service.DrugstoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drugstores")
public class DrugstoreController {
	
	private final DrugstoreService drugstoreService;
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-21
     *  @param		: void
     *  @return		: List<Drugstore>
     * 	@explain	: 약국 리스트 조회
     * */	
	@GetMapping("/getDrugstoreList")
	public ResponseEntity<?> getDrugstoreList(){
		List<Drugstore> list = drugstoreService.getDrugstoreList();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-19
     *  @param		: String keyword
     *  @return		: ResponseEntity
     * 	@explain	: 검색어로 약국 검색
     * 
     * */
	@GetMapping("/getDrugstoreListByKeyword")
    public ResponseEntity<?> getDrugstoreListByKeyword(@RequestParam(required = false) String keyword){
        List<Drugstore> list = drugstoreService.getDrugstoreListByKeyword(keyword);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-21
     *  @param		: Integer drugstoreId
     *  @return		: List<DrugstoreHistory>
     * 	@explain	: 약국 히스토리 조회
     * */	
	@GetMapping("/getDrugstoreHistoryList")
	public ResponseEntity<?> getDrugstoreHistoryList(@RequestParam Integer drugstoreId){
		List<DrugstoreHistory> list = drugstoreService.getDrugstoreHistoryList(drugstoreId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	} 

	/**
     * 	@author 	: 이성규	 
     *  @created	: 2024-01-24
     *  @param		: Integer drugstoreId
     *  @return		: List<Inquiry>
     * 	@explain	: 약국) 문의 목록 조회 
     * */	
	@GetMapping("getDrugstoreInquiry")
	public ResponseEntity<?> getDrugstoreInquiry(@RequestParam Integer drugstoreId){
		List<Inquiry> list = drugstoreService.getDrugstoreInquiry(drugstoreId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-26
     *  @param		: Integer drugstoreId
     *  @return		: ResponseEntity
     * 	@explain	: 약국 및 약국 운영시간 정보 단건 조회
     * */
	@GetMapping("/searchDrugstoreAndAvail")
	public ResponseEntity<?> searchDrugstoreAndAvail(@RequestParam Integer drugstoreId){
		System.out.println("*************************************");
		System.out.println("searchDrugstoreAndAvail 진입 > 약국ID 값 > " + drugstoreId);
		
		return null;
	}
	
	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-26
     *  @param		: Drugstore drugstoreData
     *  @return		: ResponseEntity
     * 	@explain	: 약국 정보 수정
     * */
	@PostMapping("/updateDrugstoreInfo")
	public ResponseEntity<?> updateDrugstoreInfo(@RequestBody Drugstore storeData){
//		ResponseEntity<?> responseEntity = drugstoreService.updateDrugstoreInfo(storeData);
//    	return new ResponseEntity<>(responseEntity, responseEntity.getHeaders(), responseEntity.getStatusCode());
		System.out.println("**********************************");
		System.out.println("updateDrugstoreInfo 진입 > 데이터 값 : " + storeData.toString());
		return null;
	}
}
