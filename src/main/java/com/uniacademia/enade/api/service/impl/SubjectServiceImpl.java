package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Subject;
import com.uniacademia.enade.api.repository.SubjectRepository;
import com.uniacademia.enade.api.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	private static final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public void deleteById(Long id) {
		log.info("Removendo uma assunto pelo 'Id': {}", id);
		subjectRepository.deleteById(id);
	}

	@Override
	public Optional<Subject> findById(Long id) {
		log.info("Buscando uma assunto pelo 'Id': {}", id);
		return subjectRepository.findById(id);
	}

	@Override
	public Subject persistir(Subject subject) {
		log.info("Persistindo uma assunto: {}", subject);
		return this.subjectRepository.save(subject);
	}

	@Override
	public Page<Subject> findAll(PageRequest pageRequest) {
		log.info("Buscando todas as assuntos paginadas");
		return subjectRepository.findAll(pageRequest);
	}
}
