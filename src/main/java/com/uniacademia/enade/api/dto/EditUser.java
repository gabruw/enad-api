package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditUser extends InsertUser implements Serializable {
	private static final long serialVersionUID = 4919296048494520957L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	@Override
	public String toString() {
		return "EditUser [id=" + id + ", getName()=" + getName() + ", getCpf()=" + getCpf() + ", getBirth()="
				+ getBirth() + ", getUserTypeId()=" + getUserTypeId() + ", getPicture()=" + getPicture() + "]";
	}
}
