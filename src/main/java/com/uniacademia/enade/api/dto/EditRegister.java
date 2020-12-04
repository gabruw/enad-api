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
public class EditRegister implements Serializable {
	private static final long serialVersionUID = -861105215106807581L;

	private EditUser user;
	private Login authentication;

	public static User buildEditRegister(EditUser editUser, Authentication authentication) {
		User user = new User();
		user.setId(editUser.getId());
		user.setCpf(editUser.getCpf());
		user.setName(editUser.getName());
		user.setBirth(editUser.getBirth());
		user.setPicture(editUser.getPicture());

		user.setAuthentication(authentication);

		return user;
	}

	@Override
	public String toString() {
		return "EditRegister [user=" + user.toString() + ", authentication=" + authentication.toString() + "]";
	}
}
