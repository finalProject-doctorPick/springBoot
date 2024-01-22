package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Drugstore;

import com.example.domain.DrugstoreHistory;

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
        
        System.out.println("keyword ======> " + keyword);
        System.out.println("list ======> " + list.size());
        
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
		System.out.println("getDrugstoreHistoryList 진입");
		System.out.println("param 값 : " + drugstoreId);
		List<DrugstoreHistory> list = drugstoreService.getDrugstoreHistoryList(drugstoreId);
		System.out.println("getDrugstoreHistoryList 리턴 전 list 크기 : " + list.size());
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	} 

}
