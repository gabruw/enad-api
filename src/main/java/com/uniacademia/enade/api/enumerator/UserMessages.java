package com.uniacademia.enade.api.enumerator;

public enum UserMessages {
	ALREADYEXISTSCPF("alreadyexists.cpf");

	private final String text;

	UserMessages(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
