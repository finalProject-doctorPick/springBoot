package com.example.impl;

import java.util.HashMap;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.domain.ServerResponse;
import com.example.service.SMSService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SMSServiceImpl implements SMSService{

	/**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-24
     *  @param		: String memberTel, String sendMessage
     *  @return		: ResponseEntity
     * 	@explain	: SMS 전송
     * */ 
	@Transactional
	public ResponseEntity<?> sendSMSMessage(String userTel, String sendMessage) {
		ServerResponse response = new ServerResponse();
		String api_key = "NCSMTMHYMN24T6NR";
        String api_secret = "N3KLEUAL02RZQSTQQ9P74WCOKEW4P9Y5";
        String text = "안녕하세요. DOCTORPICK입니다 :)\n";
        text = text + sendMessage;
        
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", userTel);
        params.put("from", "01082229392");
        params.put("type", "SMS");
        params.put("text", text);
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            log.debug(String.valueOf(obj));
        } catch (CoolsmsException e) {
        	log.error(e.getMessage());
        }
        
        response.setSuccess(true);
        response.setMessage("SMS 전송 성공");
        return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
