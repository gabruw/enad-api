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
public class EditCategory extends IncludeCategory implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	public static Category buildEditCategory(EditCategory editCategory) {
		Category category = new Category();
		category.setId(editCategory.getId());
		category.setDescription(editCategory.getDescription());

		return category;
	}

	@Override
	public String toString() {
		return "EditCategory [id=" + id + ", description=" + getDescription() + "]";
	}

}
