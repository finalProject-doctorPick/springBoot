package com.example.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberDAO;
import com.example.domain.DashBoard;
import com.example.domain.Member;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.MemberEntity;
import com.example.entity.RoleEntity;
import com.example.repository.MemberRepository;
import com.example.repository.RoleRepository;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberDAO memberDAO;
	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-11
     *  @param		: UserSignupDTO memberData
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 등록
     * */
    @Transactional
	public ServerResponse registerMember(Users memberData) {
    	MemberEntity insertMember = modelMapper.map(memberData, MemberEntity.class);
    	ServerResponse userSignupResponseDTO = new ServerResponse();
    	
    	String role = memberData.getUserAuth().equals("A")? "ROLE_ADMIN" : "ROLE_USER";
    	
    	RoleEntity memberRole = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found"));
    	
    	insertMember.addRole(memberRole);
    	insertMember.setMemberEmail(memberData.getUserEmail());
    	insertMember.setMemberName(memberData.getUserName());
    	insertMember.setMemberPwd(passwordEncoder.encode(memberData.getUserPwd()));
    	insertMember.setMemberBirth(memberData.getUserBirth());
    	insertMember.setMemberAuth(memberData.getUserAuth());
    	insertMember.setMemberSex(memberData.getUserSex());
    	insertMember.setMemberTel(memberData.getUserTel().replaceAll("-", ""));
    	insertMember.setMemberAddrMain(memberData.getUserAddrMain());
    	insertMember.setMemberAddrDetail(memberData.getUserAddrDetail());
    	insertMember.setMemberPoint(0);
    	
    	// 회원정보 저장
        MemberEntity result = memberRepository.save(insertMember);
        
        userSignupResponseDTO.setSuccess(true);
        userSignupResponseDTO.setMessage("DOCTORPICK 회원가입이 완료되었습니다. 환영합니다.");
        userSignupResponseDTO.setUserAuth(result.getMemberAuth());
        userSignupResponseDTO.setUserId(result.getMemberId());
        userSignupResponseDTO.setUserName(result.getMemberName());
        
		return userSignupResponseDTO;
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-12
     *  @param		: String email
     *  @return		: ResponseEntity
     * 	@explain	: 일반 회원 이메일 중복 확인
     * */
    @Transactional(readOnly = true)
	public boolean existsByMemberEmail(String email) {
		return memberRepository.existsByMemberEmail(email);
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-12
     *  @param		: String email, String pwd
     *  @return		: Member
     * 	@explain	: 일반 회원 조회
     * */
    @Transactional(readOnly = true)
	public Member findByMemberEmailAndPwd(String email, String pwd) {
    	Member m = findMemberByEmail(email);
    	// 비밀번호 체크
    	if(m != null){
    		return (passwordEncoder.matches(pwd, m.getMemberPwd())) ? m : null;
    	}else {
    		return null;
    	}
	}
    
    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-16
     *  @param		: String email
     *  @return		: MemberEntity
     * 	@explain	: 회원 정보 조회
     * */
    @Transactional(readOnly = true)
    public MemberEntity getMember(String email){
        return memberRepository.findByMemberEmail(email);
    }

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-16
     *  @param		: String email
     *  @return		: MemberEntity
     * 	@explain	: 회원 정보 조회
     * */
    @Transactional(readOnly = true)
	public List<?> getMemberCurrentHistory(Integer memberId) {
		List<Member> response = memberDAO.getMemberCurrentHistory(memberId);
		
		return response;
	}

    /**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-22
     *  @param		: 
     *  @return		: List<DashBoard>
     * 	@explain	: 관리자) 통계 - 나이대별 회원 조회
     * */
	@Override
	public List<DashBoard> getMembersCntByAge() {

		return memberDAO.getMembersCntByAge();
	}

	/**
     * 	@author 	: 정하림 
     *  @created	: 2024-01-24
     *  @param		: 
     *  @return		: List<Member>
     * 	@explain	: 관리자) 문의 관리 - 의사 문의 목록 조회
     * */
	@Override
	public List<Member> getMemberInquiryList() {
		
		return memberDAO.getMemberInquiryList();
	}

	/**
     * 	@author 	: 이성규 
     *  @created	: 2024-01-25
     *  @param		: MemberEntity
     *  @return		: List<?>
     * 	@explain	: 회원 리뷰 조회
     * */
	@Override
	public List<?> getMemberReview(Integer memberId) {
		return memberDAO.getMemberReview(memberId);
		
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-26
     *  @param		: String memberEmail
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 정보 조회 
     * */
	@Transactional(readOnly = true)
	public Member findMemberByEmail(String memberEmail) {
		return memberDAO.findMemberByEmail(memberEmail);
	}
}