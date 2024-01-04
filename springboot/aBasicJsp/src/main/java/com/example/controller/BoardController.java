package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@RequestMapping("/getBoardList")
	public void getBoardList() {
		logger.info("<<<<<getBoardList>>>>>>");
	}
	
}
