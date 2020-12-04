package com.uniacademia.enade.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.dto.Option;
import com.uniacademia.enade.api.entity.Category;

public interface CategoryService {

	void deleteById(Long id);

	List<Option> findOptions();
	
	Optional<Category> findById(Long id);

	Category persistir(Category category);

	Page<Category> findAll(PageRequest pageRequest);
}