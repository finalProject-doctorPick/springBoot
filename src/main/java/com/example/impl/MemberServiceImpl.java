package com.example.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.MemberDAO;
import com.example.domain.DashBoard;
import com.example.domain.Inquiry;
import com.example.domain.Member;
import com.example.domain.Reservation;
import com.example.domain.ServerResponse;
import com.example.domain.Users;
import com.example.entity.MemberEntity;
import com.example.entity.RoleEntity;
import com.example.repository.MemberRepository;
import com.example.repository.RoleRepository;
import com.example.service.FilesService;
import com.example.service.MemberService;
import com.example.service.ValidationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberDAO memberDAO;
	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final FilesService filesService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final ValidationService validationService;
    
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

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-27
     *  @param		: Member updateMemberData
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 정보 수정
     * */
	@Transactional
	public ResponseEntity<?> updateMemberInfo(Member updateMemberData) {
		ServerResponse response = new ServerResponse();
		List<String[]> checkValues = new ArrayList<>();
		
		checkValues.add(new String[]{"name", "이름", updateMemberData.getMemberName()});
		if(!updateMemberData.getMemberPwd().equals("")) {
			checkValues.add(new String[]{"password", "비밀번호", updateMemberData.getMemberPwd()});	
		}
		checkValues.add(new String[]{"", "전화번호", updateMemberData.getMemberTel()});
		
		ResponseEntity<?> validationResponse = validationService.checkValue(checkValues);
		if (validationResponse != null) {
            return validationResponse;
        }
		
		int updateYn = memberDAO.updateMemberInfo(updateMemberData);
		if(updateYn == 0) {
			response.setSuccess(false);
			response.setMessage("회원정보 수정이 실패되었습니다.");
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}else {
			response.setSuccess(true);
			response.setMessage("회원정보 수정이 완료되었습니다.");
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

    /**
     * 	@author 	: 백두산	 
     *  @created	: 2024-01-27
     *  @param		: Integer memberId
     *  @return		: ResponseEntity
     * 	@explain	: 일반회원 문의 조회
     * */
	@Transactional(readOnly = true)
	public List<Inquiry> getMemberInquiryList(String userEmail) {
		List<Inquiry> list = memberDAO.getMemberInquiryList(userEmail);
		return list;
	}
	
    /**
     * 	@author 	: 이성규	 
     *  @created	: 2024-01-27
     *  @param		: Reservation reservationData
     *  @return		: ResponseEntity
     * 	@explain	: 진료 접수
     * */
	@Override
	public void registReservation(Reservation reservationData,  List<MultipartFile> file) {
		Reservation reservation = new Reservation();
		 reservation.setMemberId(reservationData.getMemberId());
		 reservation.setDoctorId(reservationData.getDoctorId());
		 reservation.setReservationDate(reservationData.getReservationDate());
		 reservation.setReservationStatus(reservationData.getReservationStatus());
		 reservation.setReservationPayment(reservationData.getReservationPayment());
		 reservation.setFileKey(reservationData.getFileKey());
		 reservation.setPatientComments(reservationData.getPatientComments());
		 
		 memberDAO.registReservation(reservation); 
		 if (file != null && !file.isEmpty()) {
		        String fileKey = filesService.fileupload(file, reservationData.getFileKey());
		        // 
		    }
		
	}
	
}