package com.uniacademia.enade.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.entity.Test;

public interface TestService {

	void deleteById(Long id);

	Optional<Test> findById(Long id);

	Test persistir(Test test);

	Page<Test> findAll(PageRequest pageRequest);
}