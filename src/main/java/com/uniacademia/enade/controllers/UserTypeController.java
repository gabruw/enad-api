package com.uniacademia.enade.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.entity.UserType;
import com.uniacademia.enade.response.Response;
import com.uniacademia.enade.service.UserTypeService;

@RestController
@RequestMapping("/user-type")
@CrossOrigin(origins = "*")
public class UserTypeController {
	private static final Logger log = LoggerFactory.getLogger(UserTypeController.class);

	@Autowired
	private UserTypeService userTypeService;

	public UserTypeController() {

	}

	@PostMapping
	public ResponseEntity<Response<UserType>> register(@Valid @RequestBody UserType userType, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Tipo de Usuário: {}", userType.toString());
		Response<UserType> response = new Response<UserType>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Tipo de Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.userTypeService.persistir(userType);
		response.setData(userType);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping
	public ResponseEntity<Response<Long>> remove(@Valid @RequestBody Long id, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Removendo Tipo de Usuário: {}", id);
		Response<Long> response = new Response<Long>();
		this.userTypeService.deleteById(id);
		
		response.setData(id);
		return ResponseEntity.ok(response);
	}
}
