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
	private String name;

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;

	@Column(name = "birth", nullable = false)
	private Date birth;

	@Column(name = "picture", nullable = true)
	private String picture;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserType userType;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Authentication authentication;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subject> subjects;

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birth=" + birth + ", picture=" + picture
				+ ", userType=" + userType + ", authentication=" + authentication + ", subjects=" + subjects + "]";
	}
}
