package com.uniacademia.enade.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.entity.Category;

public interface CategoryService {

	void deleteById(Long id);

	Optional<Category> findById(Long id);

	Category persistir(Category category);

	Page<Category> findAll(PageRequest pageRequest);
}