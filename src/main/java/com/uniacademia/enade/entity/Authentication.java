package com.uniacademia.enade.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.uniacademia.enade.dto.Login;

@Table(name = "authentication")
@Entity(name = "authentication")
public class Authentication implements Serializable {
	private static final long serialVersionUID = 3259716520308178951L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Authentication convertLoginToAuthentication(Login login) {
		Authentication authentication = new Authentication();
		authentication.setEmail(login.getEmail());
		authentication.setPassword(login.getPassword());

		return authentication;
	}

	@Override
	public String toString() {
		return "Authentication [id=" + id + ", email=" + email + ", password=" + password + ", user=" + user + "]";
	}
}
