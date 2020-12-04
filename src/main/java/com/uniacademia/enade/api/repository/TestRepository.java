package com.uniacademia.enade.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.Test;

public interface TestRepository extends JpaRepository<Test, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Optional<Test> findById(Long id);

	@Transactional(readOnly = true)
	Page<Test> findAll(Pageable pageable);
}
