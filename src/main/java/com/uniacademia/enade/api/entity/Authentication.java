package com.uniacademia.enade.api.entity;

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
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "authentication")
@Entity(name = "authentication")
public class Authentication implements Serializable {
	private static final long serialVersionUID = 3259716520308178951L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", unique = true, nullable = false)
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 40 caracteres.")
	private String email;

	@Column(name = "password", nullable = false)
	@Size(min = 6, max = 40, message = "O campo 'Nome' deve conter entre 6 e 40 caracteres.")
	private String password;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User user;

	@Override
	public String toString() {
		return "Authentication [id=" + id + ", email=" + email + ", password=" + password + ", user=" + user + "]";
	}
}
