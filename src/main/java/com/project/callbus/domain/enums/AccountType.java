package com.project.callbus.domain.enums;

public enum AccountType {
	LESSOR("임대인"), REALTOR("공인중개사"), LESSEE("임차인");

	private final String contents;

	AccountType(String contents) {
		this.contents = contents;
	}
}
