package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Test;
import com.uniacademia.enade.api.repository.TestRepository;
import com.uniacademia.enade.api.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	private static final Logger log = LoggerFactory.getLogger(TestServiceImpl.class);

	@Autowired
	private TestRepository testRepository;

	@Override
	public void deleteById(Long id) {
		log.info("Removendo uma prova pelo 'Id': {}", id);
		testRepository.deleteById(id);
	}

	@Override
	public Optional<Test> findById(Long id) {
		log.info("Buscando uma prova pelo 'Id': {}", id);
		return testRepository.findById(id);
	}

	@Override
	public Test persistir(Test test) {
		log.info("Persistindo uma prova: {}", test);
		return this.testRepository.save(test);
	}

	@Override
	public Page<Test> findAll(PageRequest pageRequest) {
		log.info("Buscando todas as provas paginadas");
		return testRepository.findAll(pageRequest);
	}
}
