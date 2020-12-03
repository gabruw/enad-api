package com.uniacademia.enade.api.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Size;

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

	@Size(min = 1, max = 200, message = "O campo 'Nome' deve conter entre 1 e 200 caracteres.")
	private String name;

	private Date date;

	private User user;

	private Report report;

	private Subject subject;

	private List<Question> questions;

	public static Test buildIncludeTest(IncludeTest includeTest) {
		Test test = new Test();
		test.setUser(includeTest.getUser());
		test.setDate(includeTest.getDate());
		test.setReport(includeTest.getReport());
		test.setSubject(includeTest.getSubject());
		test.setQuestions(includeTest.getQuestions());

		return test;
	}

	@Override
	public String toString() {
		return "IncludeTest [name=" + name + ", date=" + date + ", user=" + user + ", report=" + report + ", subject="
				+ subject + ", questions=" + questions + "]";
	}
}