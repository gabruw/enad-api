package com.uniacademia.enade.api.entity;

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
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "report")
@Entity(name = "report")
public class Report implements Serializable {
	private static final long serialVersionUID = 224965383439056754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "value", nullable = true)
	@NotNull(message = "O campo 'Valor' não pode ser nulo.")
	private Double value;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	@Override
	public String toString() {
		return "Report [id=" + id + ", value=" + value + ", user=" + user + ", tests=" + tests + "]";
	}
}
