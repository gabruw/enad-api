package com.uniacademia.enade.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
@Entity(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 4753030548887476398L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@CPF(message = "O campo 'CPF' é inválido.")
	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@Column(name = "birth", nullable = false)
	@NotNull(message = "O campo 'Data de Nascimento' é obrigatório.")
	private Date birth;

	@Column(name = "picture", nullable = true)
	@Size(max = 2500, message = "O campo 'Imagem' deve conter no máximo 2500 caracteres.")
	private String picture;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserType userType;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Authentication authentication;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birth=" + birth + ", picture=" + picture
				+ ", userType=" + userType + ", tests=" + tests + ", authentication=" + authentication + "]";
	}
}
