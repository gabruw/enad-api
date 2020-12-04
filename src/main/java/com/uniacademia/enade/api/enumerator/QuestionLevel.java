package com.uniacademia.enade.api.enumerator;

public enum QuestionLevel {
	EASY("Fácil"),
	MEDIUM("Médio"),
	HARD("Difícil");

	private final String text;

	QuestionLevel(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
