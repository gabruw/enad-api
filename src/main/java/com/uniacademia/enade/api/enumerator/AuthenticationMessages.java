package com.uniacademia.enade.api.enumerator;

public enum AuthenticationMessages {
	NONEXISTENT("nonexistent"),
	INVALIDPASSWORD("invalid.password"),
	INVALIDEMAIL("invalid.email");
	
	private final String text;

	AuthenticationMessages(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
