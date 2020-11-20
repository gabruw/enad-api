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
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	private Question question;

	@Override
	public String toString() {
		return "Category [id=" + id + ", description=" + description + ", question=" + question + "]";
	}
}
