package com.project.callbus.web.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.project.callbus.domain.enums.AccountType;
import com.project.callbus.domain.enums.Quit;
import com.project.callbus.domain.member.entity.Member;

import lombok.Data;

@Data
public class MemberDto {

	private String accountId;
	private String password;
	private String nickname;
	private AccountType accountType;
	private Quit quit;

	public Member toEntity(PasswordEncoder encoder) {
		return new Member(nickname, accountType, accountId, encoder.encode(password), Quit.NO);
	}
}
