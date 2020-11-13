package com.uniacademia.enade.api.service;

import java.util.Optional;

import com.uniacademia.enade.api.entity.Category;

public interface CategoryService {

	Optional<Category> findById(Long id);

	void deleteById(Long id);

	Category persistir(Category category);
}