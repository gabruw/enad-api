package com.uniacademia.enade.entity;

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

import com.uniacademia.enade.dto.InsertUser;

@Entity
@Table(name = "user")
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

	@Column(name = "picture", nullable = false)
	private String picture;

	@ManyToOne(fetch = FetchType.EAGER)
	private UserType userType;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Authentication authentication;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subject> subject;

	public User() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public static User buildUser(InsertUser insertUser, Authentication authentication, UserType userType) {
		User user = new User();
		user.setCpf(insertUser.getCpf());
		user.setName(insertUser.getName());
		user.setBirth(insertUser.getBirth());
		user.setPicture(insertUser.getPicture());

		user.setUserType(userType);
		user.setAuthentication(authentication);
		user.setSubject(new ArrayList<Subject>());

		return user;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", cpf=" + cpf + ", birth=" + birth + ", picture=" + picture
				+ ", userType=" + userType.toString() + ", authentication=" + authentication.toString() + ", subject="
				+ subject.toString() + "]";
	}
}
