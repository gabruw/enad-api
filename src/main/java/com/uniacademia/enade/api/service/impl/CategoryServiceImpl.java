package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Category;
import com.uniacademia.enade.api.repository.CategoryRepository;
import com.uniacademia.enade.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Optional<Category> findById(Long id) {
		log.info("Buscando um Categoria por Id {}", id);
		return categoryRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo Categoria por Id {}", id);
		categoryRepository.deleteById(id);
	}

	@Override
	public Category persistir(Category category) {
		log.info("Persistindo Categoria: {}", category);
		return this.categoryRepository.save(category);
	}
}
