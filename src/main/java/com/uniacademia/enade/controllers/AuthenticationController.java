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

import com.uniacademia.enade.dto.Login;
import com.uniacademia.enade.entity.Authentication;
import com.uniacademia.enade.response.Response;
import com.uniacademia.enade.service.AuthenticationService;

@RestController
@RequestMapping("/authentication")
@CrossOrigin(origins = "*")
public class AuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

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
			log.info("Autenticação não encontrada para o Email: {}", login.getEmail());
			response.getErrors().add(String.format("Email %s não encontrado.", login.getEmail()));

			return ResponseEntity.badRequest().body(response);
		}

		if (login.getPassword() != returned.get().getPassword()) {
			log.info("Autenticação com o Password incorreto: {}", login.getEmail());
			response.getErrors().add("Password incorreto.");

			return ResponseEntity.status(403).body(response);
		}

		response.setData(returned.get());
		return ResponseEntity.ok(response);
	}
}
