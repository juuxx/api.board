package com.project.callbus.common.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

import com.project.callbus.common.config.security.dto.CustomUserDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailChecker implements UserDetailsChecker {

	@Override
	public void check(UserDetails toCheck) {
		if (toCheck instanceof CustomUserDetail) {
			CustomUserDetail customUserDetail = (CustomUserDetail)toCheck;
			if (customUserDetail.isQuit()) {
				//탈퇴
				log.debug("탈퇴한 회원임.");
			}
		}

	}
}
