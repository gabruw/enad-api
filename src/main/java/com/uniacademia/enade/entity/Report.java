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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_type")
public class Report implements Serializable {
	private static final long serialVersionUID = 224965383439056754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "value", nullable = false)
	private Double value;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "user_type", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	public Report() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", value=" + value + ", user=" + user + ", tests=" + tests + "]";
	}
}
