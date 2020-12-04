package com.uniacademia.enade.api.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.uniacademia.enade.api.entity.Question;
import com.uniacademia.enade.api.entity.Report;
import com.uniacademia.enade.api.entity.Subject;
import com.uniacademia.enade.api.entity.Test;
import com.uniacademia.enade.api.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IncludeTest implements Serializable {
	private static final long serialVersionUID = -2837163525340866355L;

	@NotEmpty(message = "O campo 'Data' é obrigatório.")
	private Date date;

	private User user;

	private Report report;

	private Subject subject;

	private List<Question> questions;

	public static Test buildIncludeTest(IncludeTest includeTest) {
		Test test = new Test();
		test.setDate(includeTest.getDate());
		test.setUser(includeTest.getUser());
		test.setReport(includeTest.getReport());
		test.setSubject(includeTest.getSubject());
		test.setQuestions(includeTest.getQuestions());

		return test;
	}

	@Override
	public String toString() {
		return "IncludeTest [date=" + date + ", user=" + user + ", report=" + report + ", subject=" + subject
				+ ", questions=" + questions + "]";
	}
}