package com.uniacademia.enade.api.enumerator;

public enum AuthenticationMessages {
	NONEXISTENT("nonexistent"),
	INVALIDEMAIL("invalid.email"),
	WITHOUTTOKEN("without.token"),
	INVALIDTOKEN("invalid.token"),
	INVALIDPASSWORD("invalid.password");
	
	private final String text;

	AuthenticationMessages(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
