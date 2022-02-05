package com.project.callbus.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.callbus.domain.enums.AccountType;
import com.project.callbus.domain.enums.Quit;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity @Getter
public class Member {

	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nickname;

	@Column
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@Column(unique=true)
	private String accountId;

	@Column
	@Enumerated(EnumType.STRING)
	private Quit quit;

	@Builder
	public Member(String nickname, AccountType accountType, String accountId, Quit quit) {
		this.nickname = nickname;
		this.accountType = accountType;
		this.accountId = accountId;
		this.quit = quit;
	}
}
