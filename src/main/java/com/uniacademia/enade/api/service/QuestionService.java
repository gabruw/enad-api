package com.uniacademia.enade.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.entity.Question;

public interface QuestionService {

	void deleteById(Long id);

	Optional<Question> findById(Long id);

	Question persistir(Question question);

	Page<Question> findAll(PageRequest pageRequest);
}