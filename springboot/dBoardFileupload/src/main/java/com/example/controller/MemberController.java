package com.example.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.FileVO;
import com.example.domain.Member;
import com.example.service.MemberService;
import com.example.util.MD5Generator;

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
	
	@RequestMapping("/join")
	public String join(@RequestParam("file")MultipartFile file, Member v) {
		String originFilename = file.getOriginalFilename();

		if(originFilename != null & !originFilename.equals("")) {
			try {
				String filename = new MD5Generator(originFilename).toString();
				
				String savePath = System.getProperty("user.dir") + "\\files";
				if( !new File(savePath).exists()) {
					new File(savePath).mkdir();
				}
				
				String filepath = savePath + "\\" + filename;
				
				// 파일 저장
				file.transferTo(new File(filepath));
				
				// DB 저장
				FileVO f = new FileVO();
				f.setOriginFilename(originFilename);
				f.setFilename(filename);
				f.setFilepath(filepath);
				
				memberService.saveBoard(v, f);
			}catch (Exception e) {
				System.out.println("파일 업로드 실패 : " + e.getMessage());
			}
			
		}else {
			memberService.saveBoard(v, null);
		}
		return "member/loginSuccess";
	}
	
}
