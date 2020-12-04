package com.uniacademia.enade.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	List<Question> findAll();

	@Transactional(readOnly = true)
	Optional<Question> findById(Long id);

	@Transactional(readOnly = true)
	Page<Question> findAll(Pageable pageable);
}
