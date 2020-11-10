package com.uniacademia.enad.dto;

import com.uniacademia.enad.entity.Authentication;

public class Login {
	private String email;
	private String password;

	public Login() {

	}

	public Login(Authentication authentication) {
		this.email = authentication.getEmail();
		this.password = authentication.getPassword();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}
}
