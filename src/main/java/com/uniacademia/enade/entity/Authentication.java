package com.uniacademia.enade.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.uniacademia.enade.dto.Login;

@Entity
@Table(name = "authentication")
public class Authentication implements Serializable {
	private static final long serialVersionUID = 3259716520308178951L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	public Authentication() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public static Authentication convertLoginToAuthentication(Login login) {
		Authentication authentication = new Authentication();
		authentication.setEmail(login.getEmail());
		authentication.setPassword(login.getPassword());

		return authentication;
	}

	@Override
	public String toString() {
		return "Authentication [id=" + id + ", email=" + email + ", password=" + password + "]";
	}
}
