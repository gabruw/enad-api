package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.Authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Login implements Serializable {
	private static final long serialVersionUID = -861105215106807581L;

	@Email(message = "O campo 'Email' é inválido.")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres.")
	private String email;

	@Size(min = 6, max = 40, message = "O campo 'Senha' deve conter entre 6 e 40 caracteres.")
	private String password;

	public static Authentication buildAuthentication(Login login) {
		Authentication authentication = new Authentication();
		authentication.setEmail(login.getEmail());
		authentication.setPassword(login.getPassword());

		return authentication;
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}
}
