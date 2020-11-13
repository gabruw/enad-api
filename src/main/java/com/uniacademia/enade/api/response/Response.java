package com.uniacademia.enade.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class Response<T> {

	@Getter
	private T data;
	private List<String> errors;

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public boolean hasErrors() {
		return this.errors.size() > 0;
	}
}
