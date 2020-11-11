package com.uniacademia.enade.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class InsertUser implements Serializable {
	private static final long serialVersionUID = 4919296048494520957L;

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@CPF(message = "O campo 'CPF' é inválido.")
	private Long cpf;

	@NotEmpty(message = "O campo 'Data de Nascimento' é obrigatório.")
	private Date birth;

	@NotEmpty(message = "O campo 'Tipo de Usuário' é obrigatório.")
	private Long userTypeId;

	private String picture;

	public InsertUser() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "InsertUser [name=" + name + ", cpf=" + cpf + ", birth=" + birth + ", userTypeId=" + userTypeId
				+ ", picture=" + picture + "]";
	}
}
