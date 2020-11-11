package com.uniacademia.enade.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.uniacademia.enade.entity.Answer;
import com.uniacademia.enade.entity.Authentication;
import com.uniacademia.enade.entity.Category;
import com.uniacademia.enade.entity.Question;
import com.uniacademia.enade.entity.Report;
import com.uniacademia.enade.entity.Subject;
import com.uniacademia.enade.entity.Test;
import com.uniacademia.enade.entity.User;
import com.uniacademia.enade.entity.UserType;

@Configuration
@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackageClasses = { Answer.class, Authentication.class, Category.class, Question.class, Report.class,
		Subject.class, Test.class, User.class, UserType.class })
@ComponentScan(basePackageClasses = { Answer.class, Authentication.class, Category.class, Question.class, Report.class,
		Subject.class, Test.class, User.class, UserType.class })
@EnableJpaRepositories(basePackageClasses = { Answer.class, Authentication.class, Category.class, Question.class,
		Report.class, Subject.class, Test.class, User.class, UserType.class })
public class EnadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnadApiApplication.class, args);
	}

}
