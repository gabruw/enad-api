package com.uniacademia.enade.api.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Response<T> {

	@Getter
	@Setter
	private T data;
	private List<ResponseError> errors;

	public Response() {
		if (this.errors == null) {
			this.errors = new ArrayList<ResponseError>();
		}
	}

	public List<ResponseError> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<ResponseError>();
		}

		return errors;
	}

	public void addFieldError(String text) {
		String title = "Erro ao validar o campo";
		errors.add(ResponseError.convert(title, text));
	}

	public void addError(ResponseError responseError) {
		errors.add(responseError);
	}

	public boolean hasErrors() {
		return this.errors.size() > 0;
	}
}
