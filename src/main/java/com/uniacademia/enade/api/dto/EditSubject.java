package com.uniacademia.enade.api.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.Subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditSubject extends IncludeSubject implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 11, message = "O campo 'Id' deve conter entre 1 e 11 caracteres.")
	private Long id;

	public static Subject buildEditSubject(EditSubject editSubject) {
		Subject subject = new Subject();
		subject.setId(editSubject.getId());
		subject.setName(editSubject.getName());
		subject.setTests(editSubject.getTests());

		return subject;
	}

	@Override
	public String toString() {
		return "EditSubject [id=" + id + ", getName()=" + getName() + ", getTests()=" + getTests() + "]";
	}
}
