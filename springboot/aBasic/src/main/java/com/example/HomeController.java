package com.example;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String home(Model m) {
		
		m.addAttribute("userId", "홍길동");
		m.addAttribute("serverTime", new Date().toString());
		
		//*******************************************
		// @Controller 에선 String "home"이 아닌 "home"이라는 뷰 페이지로 이동
		return "home";
		
		/*
		 * 		뷰 페이지 이동 경로
		 * 		Spring		: /WEB-INF/views + home + .jsp
		 * 		SptingBoot	: resource / templates + home + .html
		 * */
	}
}
