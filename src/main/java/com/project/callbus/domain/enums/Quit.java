package com.project.callbus.domain.enums;

import lombok.Getter;

@Getter
public enum Quit {
	YES("탈퇴", true), NO("계속", false);

	private final String contents;
	private final boolean code;

	Quit(String contents, boolean code) {
		this.contents = contents;
		this.code = code;
	}
}
