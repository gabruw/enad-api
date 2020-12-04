package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.UserType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeUserType implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	public static UserType buildIncludeUserType(IncludeUserType includeUserType) {
		UserType userType = new UserType();
		userType.setName(includeUserType.getName());

		return userType;
	}

	@Override
	public String toString() {
		return "IncludeUserType [name=" + name + "]";
	}

}
