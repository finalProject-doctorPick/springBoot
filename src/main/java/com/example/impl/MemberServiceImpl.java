package com.example.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MemberDAO;
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
    
//    @Transactional
//    public Member addMember(MemberDTO member) {
//    	// 회원 & 관리자 구분
//        String role = member.getMemberAuth().equals("A")? "ROLE_ADMIN" : "ROLE_USER";
//
//        Role userRole = roleRepository.findByRoles(role).orElseThrow(() -> new RuntimeException("Role not found"));
//        member.addRole(userRole);
//        
//        // 회원정보 저장
//        MemberDTO saveMember = memberRepository.save(member);
//        return saveMember;
//    }
//
//    @Transactional(readOnly = true)
//    public Optional<MemberDTO> getMember(Integer memberId){
//    	return memberRepository.findById(memberId);
//    }
//    

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
    	Member m = memberDAO.findByMember(email);
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
	public List<?> getMemberCurrntHistory(Integer memberId) {
		List<Member> response = memberDAO.getMemberCurrntHistory(memberId);
		
		return response;
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-19
     *  @param		: String searchKeyword
     *  @return		: ResponseEntity
     * 	@explain	: 관리자) 회원 목록 조회
     * */
    @Transactional(readOnly = true)
	public List<?> getMemberListForAdmin(String searchKeyword) {
    	System.out.println("관리자) 회원 목록 조회 진입");
    	System.out.println("memberDAO.getMemberListForAdmin(searchKeyword) 진입 전 search값 : " + searchKeyword);
    	
		List<Member> response = memberDAO.getMemberListForAdmin(searchKeyword);
		return response;
	}
}