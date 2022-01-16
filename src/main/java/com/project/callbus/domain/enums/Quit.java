package com.project.callbus.domain.enums;

public enum Quit {
	YES("탈퇴"), NO("계속");

	private final String contents;

	Quit(String contents) {
		this.contents = contents;
	}
}
