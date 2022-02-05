package com.project.callbus.common.config.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.callbus.domain.enums.Quit;

import lombok.Builder;

public class CustomUserDetail implements UserDetails {

	private String username;
	private String accountType;
	private Quit quit;

	@Builder
	public CustomUserDetail(String username, String accountType, Quit quit) {
		this.username = username;
		this.accountType = accountType;
		this.quit = quit;

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	public boolean isQuit(){
		//회원탈퇴
		return quit.isCode();
	}
}
