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
public class EditUserType extends IncludeUserType implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	public static UserType buildEditUserType(EditUserType editUserType) {
		UserType userType = new UserType();
		userType.setId(editUserType.getId());
		userType.setName(editUserType.getName());

		return userType;
	}

	@Override
	public String toString() {
		return "EditUserType [id=" + id + ", getName()=" + getName() + "]";
	}
}
