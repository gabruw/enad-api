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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "test")
@Entity(name = "test")
public class Test implements Serializable {
	private static final long serialVersionUID = -2320783044216127463L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "description", nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Report report;

	@ManyToOne(fetch = FetchType.EAGER)
	private Subject subject;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "test_questions", joinColumns = @JoinColumn(name = "idTest"), inverseJoinColumns = @JoinColumn(name = "idQuestion"))
	private List<Question> questions;

	@Override
	public String toString() {
		return "Test [id=" + id + ", date=" + date + ", user=" + user.toString() + ", report=" + report.toString()
				+ ", subject=" + subject.toString() + ", questions=" + questions.toString() + "]";
	}
}
