package com.uniacademia.enade.api.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Optional<Subject> findById(Long id);

	@Transactional(readOnly = true)
	Page<Subject> findAll(Pageable pageable);
}
