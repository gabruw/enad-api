package com.uniacademia.enade.api.service;

import java.util.List;
import java.util.Optional;

import com.uniacademia.enade.api.entity.Category;

public interface CategoryService {

	void deleteById(Long id);
	
	List<Category> findAll();

	Optional<Category> findById(Long id);

	Category persistir(Category category);
}