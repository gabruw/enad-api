package com.uniacademia.enade.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question implements Serializable {
	private static final long serialVersionUID = 8057524850929676912L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "level", nullable = false)
	private String level;

	@Column(name = "correct", nullable = false)
	private char correct;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Category> category;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Answer> answers;

	@ManyToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	public Question() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public char getCorrect() {
		return correct;
	}

	public void setCorrect(char correct) {
		this.correct = correct;
	}

	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", description=" + description + ", level=" + level + ", correct=" + correct
				+ ", category=" + category + ", answers=" + answers + ", tests=" + tests + "]";
	}
}
