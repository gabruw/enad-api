package com.uniacademia.enade.controllers;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.dto.InsertUser;
import com.uniacademia.enade.dto.Login;
import com.uniacademia.enade.dto.Register;
import com.uniacademia.enade.entity.Authentication;
import com.uniacademia.enade.entity.User;
import com.uniacademia.enade.entity.UserType;
import com.uniacademia.enade.response.Response;
import com.uniacademia.enade.service.AuthenticationService;
import com.uniacademia.enade.service.UserService;
import com.uniacademia.enade.service.UserTypeService;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private AuthenticationService authenticationService;

	public AuthenticationController() {

	}

	@PostMapping
	public ResponseEntity<Response<Authentication>> login(@Valid @RequestBody Login login, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Buscando Autenticação: {}", login.toString());
		Response<Authentication> response = new Response<Authentication>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de login da Autenticação: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<Authentication> returned = this.authenticationService.findByEmail(login.getEmail());
		if (!returned.isPresent()) {
			log.info("Autenticação não encontrada para o email: {}", login.getEmail());
			response.getErrors().add(String.format("Email %s não encontrado.", login.getEmail()));

			return ResponseEntity.badRequest().body(response);
		}

		if (login.getPassword() != returned.get().getPassword()) {
			log.info("Autenticação com o password incorreto: {}", login.getEmail());
			response.getErrors().add("Password incorreto.");

			return ResponseEntity.status(403).body(response);
		}

		response.setData(returned.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<Register>> register(@Valid @RequestBody Register register, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Autenticação: {}", register.toString());
		Response<Register> response = new Response<Register>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro da Autenticação: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findById(register.getUser().getUserTypeId());
		if (!userType.isPresent()) {
			log.error("Erro ao validar o tipo de usuário: {}", register.getUser());
			response.getErrors().add("Erro ao validar o tipo de usuário.");

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = convertLoginToAuthentication(register.getAuthentication());
		authentication = this.authenticationService.persistir(authentication);

		User user = convertInsertUserToUser(register.getUser());
		user.setUserType(userType.get());
		user.setAuthentication(authentication);
		this.userService.persistir(user);

		response.setData(register);
		return ResponseEntity.ok(response);
	}

	public Authentication convertLoginToAuthentication(Login login) {
		Authentication authentication = new Authentication();
		authentication.setEmail(login.getEmail());
		authentication.setPassword(login.getPassword());

		return authentication;
	}

	public User convertInsertUserToUser(InsertUser insertUser) {
		User user = new User();
		user.setCpf(insertUser.getCpf());
		user.setName(insertUser.getName());
		user.setBirth(insertUser.getBirth());
		user.setPicture(insertUser.getPicture());

		return user;
	}
}
