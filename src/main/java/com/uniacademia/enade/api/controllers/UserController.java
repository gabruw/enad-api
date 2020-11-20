package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.EditRegister;
import com.uniacademia.enade.api.dto.IncludeRegister;
import com.uniacademia.enade.api.dto.Login;
import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.User;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.enumerator.UserTypeMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.AuthenticationService;
import com.uniacademia.enade.api.service.UserService;
import com.uniacademia.enade.api.service.UserTypeService;
import com.uniacademia.enade.api.utils.Messages;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private AuthenticationService authenticationService;

	public UserController() {

	}

	@PostMapping("/include")
	public ResponseEntity<Response<User>> include(@Valid @RequestBody IncludeRegister includeRegister, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", includeRegister.toString());
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para cadastro do Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findById(includeRegister.getUserType().getId());
		if (!userType.isPresent()) {
			log.error("Erro ao validar o Tipo de Usuário: {}", includeRegister.getUserType().toString());
			response.addError(Messages.getAuthenticationError(UserTypeMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = Login.buildAuthentication(includeRegister.getAuthentication());
		authentication = this.authenticationService.persistir(authentication);

		User user = IncludeRegister.buildIncludeRegister(includeRegister.getUser(), authentication, userType.get());
		user = this.userService.persistir(user);

		response.setData(user);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/edit")
	public ResponseEntity<Response<User>> edit(@Valid @RequestBody EditRegister editRegister, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Editando Usuário: {}", editRegister.toString());
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição do Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findById(editRegister.getUserType().getId());
		if (!userType.isPresent()) {
			log.error("Erro ao validar o Tipo de Usuário: {}", editRegister.getUserType().toString());
			response.addError(Messages.getAuthenticationError(UserTypeMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = Login.buildAuthentication(editRegister.getAuthentication());
		authentication = this.authenticationService.persistir(authentication);
		
		User user = EditRegister.buildEditRegister(editRegister.getUser(), authentication, userType.get());
		user = this.userService.persistir(user);

		response.setData(user);
		return ResponseEntity.ok(response);
	}
}