package com.project.callbus.common.config.security;

import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.project.callbus.domain.member.entity.Member;
import com.project.callbus.domain.member.repository.MemberRepository;
import com.project.callbus.common.config.security.dto.CustomUserDetail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomPreAuthenticationUserDetailService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
		String name = token.getName();

		if(StringUtils.hasText(name)){
			Member member = memberRepository.findByAccountId(name)
				.orElseThrow(() -> new UsernameNotFoundException("사용자 정보를 찿을수 없습니다."));

			return CustomUserDetail.builder()
					.username(name)
					.accountType(member.getAccountType().name())
					.quit(member.getQuit())
					.build();
		}

		return CustomUserDetail.builder()
			.accountType(SecurityConfig.ROLE_ANONYMOUS)
			.username("GUEST")
			.build();

	}
}
