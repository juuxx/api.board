package com.project.callbus.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.domain.member.repository.MemberRepository;
import com.project.callbus.web.dto.MemberDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder encoder;

	@Transactional
	public void joinMember(MemberDto memberDto) {
		Member member = memberDto.toEntity(encoder);
		memberRepository.save(member);
	}
}
