package com.uniacademia.enade.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.uniacademia.enade.api.dto.InsertUser;

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

	@Column(name = "cpf", nullable = false)
	private Long cpf;

	@Column(name = "birth", nullable = false)
	private Date birth;

	@Column(name = "picture", nullable = true)
	private String picture;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserType userType;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Authentication authentication;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subject> subjects;

	public static User buildUser(InsertUser insertUser, Authentication authentication, UserType userType) {
		User user = new User();
		user.setCpf(insertUser.getCpf());
		user.setName(insertUser.getName());
		user.setBirth(insertUser.getBirth());
		user.setPicture(insertUser.getPicture());

		user.setUserType(userType);
		user.setAuthentication(authentication);
		user.setSubjects(new ArrayList<Subject>());

		return user;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birth=" + birth + ", picture=" + picture
				+ ", userType=" + userType.toString() + ", authentication=" + authentication.toString() + ", subjects="
				+ subjects.toString() + "]";
	}
}
