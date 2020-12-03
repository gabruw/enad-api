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
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "subject")
@Entity(name = "subject")
public class Subject implements Serializable {
	private static final long serialVersionUID = 224965383439056754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

	@OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Test> tests;

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", user=" + user + ", tests=" + tests + "]";
	}
}
