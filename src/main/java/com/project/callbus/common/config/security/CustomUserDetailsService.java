package com.project.callbus.common.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.callbus.common.config.security.dto.CustomUserDetail;
import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByAccountId(username)
			.orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찿을수 없습니다."));

		return CustomUserDetail.builder()
			.username(username)
			.password(member.getPassword())
			.accountType(member.getAccountType().name())
			.quit(member.getQuit())
			.build();
	}
}
