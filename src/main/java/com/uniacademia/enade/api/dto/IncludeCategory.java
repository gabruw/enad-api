package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeCategory implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 200, message = "O campo 'Descrição' deve conter entre 1 e 200 caracteres.")
	private String description;

	public static Category buildIncludeCategory(IncludeCategory includeCategory) {
		Category category = new Category();
		category.setDescription(includeCategory.getDescription());

		return category;
	}

	@Override
	public String toString() {
		return "IncludeCategory [description=" + description + "]";
	}
}
