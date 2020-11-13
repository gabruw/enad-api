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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.Register;
import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.User;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.AuthenticationService;
import com.uniacademia.enade.api.service.UserService;
import com.uniacademia.enade.api.service.UserTypeService;

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

	@PostMapping
	public ResponseEntity<Response<Register>> register(@Valid @RequestBody Register register, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", register.toString());
		Response<Register> response = new Response<Register>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findById(register.getUser().getUserTypeId());
		if (!userType.isPresent()) {
			log.error("Erro ao validar o Tipo de Usuário: {}", register.getUser());
			response.getErrors().add("Erro ao validar o Tipo de Usuário.");

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = Authentication.convertLoginToAuthentication(register.getAuthentication());
		authentication = this.authenticationService.persistir(authentication);

		User user = User.buildUser(register.getUser(), authentication, userType.get());
		this.userService.persistir(user);

		response.setData(register);
		return ResponseEntity.ok(response);
	}
}
