package com.uniacademia.enade.api.service.impl;

import java.util.List;
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
	public List<Category> findAll() {
		log.info("Buscando todos as categorias");
		return categoryRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo categoria pelo 'Id': {}", id);
		categoryRepository.deleteById(id);
	}

	@Override
	public Optional<Category> findById(Long id) {
		log.info("Buscando uma categoria pelo 'Id': {}", id);
		return categoryRepository.findById(id);
	}

	@Override
	public Category persistir(Category category) {
		log.info("Persistindo categoria: {}", category);
		return this.categoryRepository.save(category);
	}
}
