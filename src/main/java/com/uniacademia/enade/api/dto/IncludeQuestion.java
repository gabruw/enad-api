package com.uniacademia.enade.api.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.uniacademia.enade.api.entity.Answer;
import com.uniacademia.enade.api.entity.Category;
import com.uniacademia.enade.api.entity.Question;
import com.uniacademia.enade.api.enumerator.QuestionLevel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeQuestion implements Serializable {
	private static final long serialVersionUID = 422892977774736101L;

	@Size(min = 1, max = 200, message = "O campo 'Descrição' deve conter entre 1 e 200 caracteres.")
	private String description;

	@Size(min = 1, max = 7, message = "O campo 'Dificuldade' deve conter entre 1 e 7 caracteres.")
	private QuestionLevel level;

	@NotNull(message = "O campo 'Resposta correta' é obrigatório.")
	private char correct;

	private List<Answer> answers;

	private List<Category> categories;

	public static Question buildIncludeQuestion(IncludeQuestion includeQuestion) {
		Question question = new Question();
		question.setLevel(includeQuestion.getLevel());
		question.setCorrect(includeQuestion.getCorrect());
		question.setDescription(includeQuestion.getDescription());

		question.setAnswers(includeQuestion.getAnswers());
		question.setCategories(includeQuestion.getCategories());

		return question;
	}

	@Override
	public String toString() {
		return "IncludeQuestion [description=" + description + ", level=" + level + ", correct=" + correct
				+ ", categories=" + categories + ", answers=" + answers + "]";
	}
}
