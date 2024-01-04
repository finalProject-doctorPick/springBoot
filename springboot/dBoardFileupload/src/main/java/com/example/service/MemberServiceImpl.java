package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.FileDAO;
import com.example.dao.MemberDAO;
import com.example.domain.FileVO;
import com.example.domain.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public Member login(Member m) {
		return memberDAO.login(m);
	}

	@Override
	public void insertMember(Member m) {
		memberDAO.insertMember(m);
	}

	@Override
	@Transactional
	public void saveBoard(Member v, FileVO f) {
		if(f != null) {
			// 파일 첨부가 있는 경우
			fileDAO.insertFile(f);
			v.setImgId(fileDAO.selectId());
		}
		memberDAO.insertMember(v);
	}
	
}
