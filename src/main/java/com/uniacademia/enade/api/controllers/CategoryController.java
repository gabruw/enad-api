package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.entity.Category;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {
	private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	public CategoryController() {

	}

	@GetMapping("/getAll")
	public ResponseEntity<Response<String>> teste() throws NoSuchAlgorithmException {
		Response<String> response = new Response<String>();

		response.setData("Batata");

		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Category>> include(@Valid @RequestBody Category category, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Categoria: {}", category.toString());
		Response<Category> response = new Response<Category>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.categoryService.persistir(category);
		response.setData(category);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/edit")
	public ResponseEntity<Response<Category>> edit(@Valid @RequestBody Category category, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Editar Categoria: {}", category.toString());
		Response<Category> response = new Response<Category>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição de Categoria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.categoryService.persistir(category);
		response.setData(category);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove")
	public ResponseEntity<Response<Long>> remove(@Valid @RequestBody Long id, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Removendo Categoria: {}", id);
		Response<Long> response = new Response<Long>();
		this.categoryService.deleteById(id);

		response.setData(id);
		return ResponseEntity.ok(response);
	}
}
