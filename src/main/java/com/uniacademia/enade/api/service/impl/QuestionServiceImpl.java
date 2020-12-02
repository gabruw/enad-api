package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Question;
import com.uniacademia.enade.api.repository.QuestionRepository;
import com.uniacademia.enade.api.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

	private static final Logger log = LoggerFactory.getLogger(QuestionServiceImpl.class);

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void deleteById(Long id) {
		log.info("Removendo uma pergunta pelo 'Id': {}", id);
		questionRepository.deleteById(id);
	}

	@Override
	public Optional<Question> findById(Long id) {
		log.info("Buscando uma pergunta pelo 'Id': {}", id);
		return questionRepository.findById(id);
	}

	@Override
	public Question persistir(Question question) {
		log.info("Persistindo uma pergunta: {}", question);
		return this.questionRepository.save(question);
	}

	@Override
	public Page<Question> findAll(PageRequest pageRequest) {
		log.info("Buscando todas as perguntas paginadas");
		return questionRepository.findAll(pageRequest);
	}
}
