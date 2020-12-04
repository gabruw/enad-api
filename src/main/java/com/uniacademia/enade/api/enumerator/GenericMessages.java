package com.uniacademia.enade.api.enumerator;

public enum GenericMessages {
	NONEXISTENT("nonexistent"), ALREADYEXISTENT("alreadyexistent");

	private final String text;

	GenericMessages(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
