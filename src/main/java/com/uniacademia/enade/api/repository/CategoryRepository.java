package com.uniacademia.enade.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Transactional(readOnly = true)
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Optional<Category> findById(Long id);
}
