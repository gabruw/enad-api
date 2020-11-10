package com.uniacademia.enad.controllers;

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

import com.uniacademia.enad.dto.Login;
import com.uniacademia.enad.entity.Authentication;
import com.uniacademia.enad.response.Response;
import com.uniacademia.enad.service.AuthenticationService;

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
	public ResponseEntity<Response<Login>> cadastrar(@Valid @RequestBody Login login, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Buscando Login: {}", login.toString());
		Response<Login> response = new Response<Login>();

		Optional<Authentication> authentication = this.authenticationService.findByEmail(login.getEmail());
		if (!authentication.isPresent()) {
			log.info("Autenticação não encontrada para o email: {}", login.getEmail());
			response.getErrors().add("Autenticação não encontrada para o email " + login.getEmail());

			return ResponseEntity.badRequest().body(response);
		}

		if (login.getEmail() != authentication.get().getPassword()) {
			log.info("Password incorreto: {}", login.getEmail());
			response.getErrors().add("Password incorreto");

			return ResponseEntity.status(403).body(response);
		}

		Login conveted = new Login(authentication.get());
		response.setData(conveted);

		return ResponseEntity.ok(response);
	}
}
