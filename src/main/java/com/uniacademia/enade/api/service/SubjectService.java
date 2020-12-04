package com.uniacademia.enade.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.entity.Subject;

public interface SubjectService {

	void deleteById(Long id);

	Optional<Subject> findById(Long id);

	Subject persistir(Subject subject);

	Page<Subject> findAll(PageRequest pageRequest);
}