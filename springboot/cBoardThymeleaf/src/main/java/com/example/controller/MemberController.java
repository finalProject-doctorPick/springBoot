package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.domain.Member;
import com.example.service.MemberService;

@Controller
@RequestMapping("/member")
@SessionAttributes("member") // Model에 "member"라는
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/{step}")
	public String viewPage(@PathVariable String step) {
		return "member/" + step;
	}
	
	public String insertMember(Member m) {
		memberService.insertMember(m);
		return "redirect:/board/getBoardList";
	}
	
	/*
	 * 	해당 함수는 일반적인 로그인 성공으로 세션처리 예제가 아닙니다.
	 *  해당 함수는 Model 데이터를 저장한 후 redirect 되는 상황일 경우
	 * */
//	@RequestMapping("login")
//	public void login(Member vo) {
//		System.out.println("MemberController login : " + vo);
//		Member result = MemberService.login(vo);
//		
//	}
	
	@RequestMapping("login")
	public String login(Member vo, Model m) {
		Member result = memberService.login(vo);
		
		if(result != null) {
			m.addAttribute("member", result);
			// return "loginSuccess";
			return "member/loginSuccess";
		}else {
			// 여기서는 뷰 페이지 지정이 가능하지만
			// 일부러 리다이렉트 상황을 만듦.
			return "redirect:loginForm";
		}
		
	}
	
}
