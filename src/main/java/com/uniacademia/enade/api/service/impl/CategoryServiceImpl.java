package com.uniacademia.enade.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.dto.Option;
import com.uniacademia.enade.api.entity.Category;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.repository.CategoryRepository;
import com.uniacademia.enade.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private static final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void deleteById(Long id) {
		log.info("Removendo uma categoria pelo 'Id': {}", id);
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Option> findOptions() {
		log.info("Buscando todas as opções das categorias");

		List<Category> userTypes = categoryRepository.findAll();
		return userTypes.stream().map(userType -> {
			Option opt = new Option();
			opt.setValue(userType.getId());
			opt.setText(userType.getDescription());
			return opt;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<Category> findById(Long id) {
		log.info("Buscando uma categoria pelo 'Id': {}", id);
		return categoryRepository.findById(id);
	}

	@Override
	public Category persistir(Category category) {
		log.info("Persistindo uma categoria: {}", category);
		return this.categoryRepository.save(category);
	}

	@Override
	public Page<Category> findAll(PageRequest pageRequest) {
		log.info("Buscando todas as categorias paginadas");
		return categoryRepository.findAll(pageRequest);
	}
}
