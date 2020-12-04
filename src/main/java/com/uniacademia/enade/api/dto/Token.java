package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Token implements Serializable {
	private static final long serialVersionUID = -4787988584324791683L;

	@NotNull(message = "O campo 'Tipo de Usuário' não pode ser vazio.")
	private String userType;

	@NotNull(message = "O campo 'Nome' não pode ser vazio.")
	private String name;

	private String picture;

	@NotNull(message = "O campo 'Token' não pode ser vazio.")
	private String token;

	@Override
	public String toString() {
		return "Token [userType=" + userType + ", name=" + name + ", picture=" + picture + ", token=" + token + "]";
	}
}
