package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Hospital;
import com.example.service.HospitalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals")
public class HospitalController {

	private final HospitalService hospitalService;
	
	@GetMapping("/getHospitalList")
	public ResponseEntity<?> getHospitalList(){
		List<Hospital> list = hospitalService.getHospitalList();
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-19
     *  @param		: String keyword
     *  @return		: ResponseEntity
     * 	@explain	: 검색어로 병원 검색
     * 
     * */
	@GetMapping("/getHospitalListByKeyword")
    public ResponseEntity<?> getHospitalListByKeyword(@RequestParam(required = false) String keyword){
        List<Hospital> list = hospitalService.getHospitalListByKeyword(keyword);
        
        System.out.println("keyword ======> " + keyword);
        System.out.println("list ======> " + list.size());
        
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-20
     *  @param		: Map requestData
     *  @return		: ResponseEntity
     * 	@explain	: 현재 위치로 병원 검색
     * 
     * */
	@PostMapping("/getCurrentLocationList")
	public ResponseEntity<?> getCurrentLocationList(@RequestBody Map<String, String> requestData){
		String addr1 = requestData.get("addr1");
	    String addr2 = requestData.get("addr2");
	   

	    List<Hospital> list = hospitalService.getCurrentLocationList(requestData);
	    System.out.println(addr1 + ", " + addr2);
	    System.out.println(list.size());

	    return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-22
     *  @param		: 
     *  @return		: ResponseEntity
     * 	@explain	: 차트 - 지역별 병원
     * 
     * */
	@GetMapping("/getHospitalRegionCnt")
	public ResponseEntity<?> getHospitalRegionCnt(){
		List<Hospital> list = hospitalService.getHospitalRegionCnt();
		System.out.println("Controller 내용" + list);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
