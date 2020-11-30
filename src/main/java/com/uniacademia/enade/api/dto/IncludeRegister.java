package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeRegister implements Serializable {
	private static final long serialVersionUID = -861105215106807581L;

	private IncludeUser user;
	private Login authentication;

	public static User buildIncludeRegister(IncludeUser insertUser, Authentication authentication) {
		User user = new User();
		user.setCpf(insertUser.getCpf());
		user.setName(insertUser.getName());
		user.setBirth(insertUser.getBirth());
		user.setPicture(insertUser.getPicture());

		user.setAuthentication(authentication);

		return user;
	}

	@Override
	public String toString() {
		return "IncludeRegister [user=" + user.toString() + ", authentication=" + authentication.toString() + "]";
	}
}
