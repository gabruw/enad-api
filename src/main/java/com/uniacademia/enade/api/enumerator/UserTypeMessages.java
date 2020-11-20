package com.uniacademia.enade.api.enumerator;

public enum UserTypeMessages {
	NONEXISTENT("nonexistent");

	private final String text;

	UserTypeMessages(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
