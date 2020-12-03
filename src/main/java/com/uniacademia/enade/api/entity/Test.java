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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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

	@Column(name = "date", nullable = false)
	@NotEmpty(message = "O campo 'Data' é obrigatório.")
	private Date date;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	private Report report;

	@ManyToOne(fetch = FetchType.EAGER)
	private Subject subject;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Question> questions;

	@Override
	public String toString() {
		return "Test [id=" + id + ", date=" + date + ", user=" + user + ", report=" + report + ", subject=" + subject
				+ ", questions=" + questions + "]";
	}
}
