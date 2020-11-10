package com.uniacademia.enad.dto;

import java.io.Serializable;

public class Register implements Serializable {
	private static final long serialVersionUID = -861105215106807581L;

	private InsertUser user;
	private Login authentication;

	public Register() {

	}

	public InsertUser getUser() {
		return user;
	}

	public void setUser(InsertUser user) {
		this.user = user;
	}

	public Login getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Login authentication) {
		this.authentication = authentication;
	}

	@Override
	public String toString() {
		return "Register [user=" + user.toString() + ", authentication=" + authentication.toString() + "]";
	}
}
