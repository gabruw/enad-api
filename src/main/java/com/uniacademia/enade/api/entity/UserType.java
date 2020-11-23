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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_type")
@Entity(name = "user_type")
public class UserType implements Serializable {
	private static final long serialVersionUID = 224965383439056754L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@OneToMany(mappedBy = "userType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<User> users;

	@Override
	public String toString() {
		return "UserType [id=" + id + ", name=" + name + ", user=" + users + "]";
	}
}
