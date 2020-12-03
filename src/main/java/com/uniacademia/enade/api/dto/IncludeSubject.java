package com.uniacademia.enade.api.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.Subject;
import com.uniacademia.enade.api.entity.Test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeSubject implements Serializable {
	private static final long serialVersionUID = -2242255198898653L;

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	private List<Test> tests;

	public static Subject buildIncludeSubject(IncludeSubject includeSubject) {
		Subject subject = new Subject();
		subject.setName(includeSubject.getName());
		subject.setTests(includeSubject.getTests());

		return subject;
	}

	@Override
	public String toString() {
		return "IncludeSubject [name=" + name + ", tests=" + tests + "]";
	}
}
