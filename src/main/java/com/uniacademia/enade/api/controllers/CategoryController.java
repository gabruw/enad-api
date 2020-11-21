package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.EditCategory;
import com.uniacademia.enade.api.dto.IncludeCategory;
import com.uniacademia.enade.api.entity.Category;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.CategoryService;
import com.uniacademia.enade.api.utils.Messages;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	public CategoryController() {

	}

	@GetMapping("/all")
	public ResponseEntity<Response<List<Category>>> all() throws NoSuchAlgorithmException {
		Response<List<Category>> response = new Response<List<Category>>();

		List<Category> categories = categoryService.findAll();
		response.setData(categories);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Category>> include(@Valid @RequestBody IncludeCategory includeCategory,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Iniciando cadastro da categoria: {}", includeCategory.toString());
		Response<Category> response = new Response<Category>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Category category = IncludeCategory.buildIncludeCategory(includeCategory);
		category = this.categoryService.persistir(category);

		response.setData(category);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/edit")
	public ResponseEntity<Response<Category>> edit(@Valid @RequestBody EditCategory editCategory, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Iniciando edição da categoria: {}", editCategory.toString());
		Response<Category> response = new Response<Category>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição de categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Category category = EditCategory.buildEditCategory(editCategory);
		category = this.categoryService.persistir(category);

		response.setData(category);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response<Category>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Iniciando remoção da categoria: {}", id);
		Response<Category> response = new Response<Category>();

		Optional<Category> category = this.categoryService.findById(id);
		if (category.isEmpty()) {
			log.info("Erro ao validar o 'Id' para remoção da categoria: {}", id);
			response.addError(Messages.getCategoriaError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		this.categoryService.deleteById(id);
		response.setData(category.get());

		return ResponseEntity.ok(response);
	}
}
