package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Register implements Serializable {
	private static final long serialVersionUID = -861105215106807581L;

	private InsertUser user;
	private Login authentication;

	@Override
	public String toString() {
		return "Register [user=" + user.toString() + ", authentication=" + authentication.toString() + "]";
	}
}
