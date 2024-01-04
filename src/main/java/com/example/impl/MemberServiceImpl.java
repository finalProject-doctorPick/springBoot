package com.example.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Member;
import com.example.domain.Role;
import com.example.repository.MemberRepository;
import com.example.repository.RoleRepository;
import com.example.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final RoleRepository roleRepository;

    
    @Transactional(readOnly = true)
    public Optional<Member> getMember(Integer memberId){
    	return memberRepository.findById(memberId);
    }
    
    @Transactional(readOnly = true)
    public Member findByEmail(String email){
        return memberRepository.findByMemberEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 사용자를 찾을 수 없습니다: " + email));
    }


    @Transactional
    public Member addMember(Member member) {
        Optional<Role> userRole = roleRepository.findByMemberName("ROLE_USER");
        member.addRole(userRole.get());
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

}
