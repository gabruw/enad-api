package com.uniacademia.enade.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "category")
@Entity(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 9152481038693970123L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Descrição' deve conter entre 1 e 200 caracteres.")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	private Question question;

	@Override
	public String toString() {
		return "Category [id=" + id + ", description=" + description + ", question=" + question + "]";
	}
}
