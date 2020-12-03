package com.uniacademia.enade.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.uniacademia.enade.api.enumerator.QuestionLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
@Entity(name = "question")
public class Question implements Serializable {
	private static final long serialVersionUID = 8057524850929676912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Descrição' deve conter entre 1 e 200 caracteres.")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "level", nullable = false)
	@Size(min = 1, max = 6, message = "O campo 'Dificuldade' deve conter entre 1 e 6 caracteres.")
	private QuestionLevel level;

	@Column(name = "correct", nullable = false)
	@NotNull(message = "O campo 'Resposta correta' não pode ser nulo.")
	private char correct;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Category> categories;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Answer> answers;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", level=" + level + ", correct=" + correct
				+ ", categories=" + categories + ", answers=" + answers + ", tests=" + tests + "]";
	}
}
