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

import com.uniacademia.enade.api.dto.Login;
import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.enumerator.AuthenticationMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.AuthenticationService;
import com.uniacademia.enade.api.utils.Messages;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/authentication")
public class AuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	private AuthenticationService authenticationService;

	public AuthenticationController() {

	}

	@PostMapping("/login")
	public ResponseEntity<Response<Authentication>> login(@Valid @RequestBody Login login, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Buscando Autenticação: {}", login.toString());
		Response<Authentication> response = new Response<Authentication>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de login da Autenticação: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<Authentication> returned = this.authenticationService.findByEmail(login.getEmail());
		if (!returned.isPresent()) {
			log.info("Autenticação não encontrada para o Email: {}", login.getEmail());
			response.addError(
					Messages.getAuthenticationError(AuthenticationMessages.INVALIDEMAIL.toString(), login.getEmail()));

			return ResponseEntity.badRequest().body(response);
		}

		if (login.getPassword() != returned.get().getPassword()) {
			log.info("Autenticação com o Password incorreto: {}", login.getPassword());
			response.addError(Messages.getAuthenticationError(AuthenticationMessages.INVALIDPASSWORD.toString(),
					login.getPassword()));

			return ResponseEntity.status(403).body(response);
		}

		response.setData(returned.get());
		return ResponseEntity.ok(response);
	}
}
