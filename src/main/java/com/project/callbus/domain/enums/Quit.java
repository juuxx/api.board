package com.project.callbus.domain.enums;

import lombok.Getter;

@Getter
public enum Quit {
	YES("íí´", true), NO("ęłě", false);

	private final String contents;
	private final boolean code;

	Quit(String contents, boolean code) {
		this.contents = contents;
		this.code = code;
	}
}
