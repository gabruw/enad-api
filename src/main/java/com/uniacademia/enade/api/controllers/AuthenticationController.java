package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.Login;
import com.uniacademia.enade.api.dto.Token;
import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.User;
import com.uniacademia.enade.api.enumerator.AuthenticationMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.security.utils.JwtTokenUtil;
import com.uniacademia.enade.api.service.AuthenticationService;
import com.uniacademia.enade.api.service.UserService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/authentication")
public class AuthenticationController {
	private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

	private static final String BEARER_PREFIX = "Bearer ";
	private static final String TOKEN_HEADER = "Authorization";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/login")
	public ResponseEntity<Response<Token>> login(@Valid @RequestBody Login login, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Iniciando busca da autenticação: {}", login.toString());
		Response<Token> response = new Response<Token>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de login da Autenticação: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<Authentication> authetication = this.authenticationService.findByEmail(login.getEmail());
		if (!authetication.isPresent()) {
			log.info("Autenticação não encontrada para o Email: {}", login.getEmail());
			response.addError(
					Messages.getAuthenticationError(AuthenticationMessages.INVALIDEMAIL.toString(), login.getEmail()));

			return ResponseEntity.status(403).body(response);
		}

		boolean isEqual = new BCryptPasswordEncoder().matches(login.getPassword(), authetication.get().getPassword());
		if (!isEqual) {
			log.info("Autenticação com o Password incorreto: {}", login.getPassword());
			response.addError(Messages.getAuthenticationError(AuthenticationMessages.INVALIDPASSWORD.toString(),
					login.getPassword()));

			return ResponseEntity.status(403).body(response);
		}

		Optional<User> user = this.userService.findById(authetication.get().getUser().getId());
		UsernamePasswordAuthenticationToken securityAuthentication = new UsernamePasswordAuthenticationToken(
				login.getEmail(), login.getPassword());

		org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(securityAuthentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		Token token = new Token();
		token.setName(user.get().getName());
		token.setPicture(user.get().getPicture());
		token.setUserType(user.get().getUserType().getName());

		UserDetails userDetails = userDetailsService.loadUserByUsername(login.getEmail());
		token.setToken(jwtTokenUtil.createToken(userDetails));

		response.setData(token);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/refresh")
	public ResponseEntity<Response<Token>> gerarRefreshTokenJwt(HttpServletRequest request) {
		log.info("Gerando refresh token JWT.");
		Response<Token> response = new Response<Token>();
		Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

		if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
			token = Optional.of(token.get().substring(7));
		}

		if (!token.isPresent()) {
			response.addError(Messages.getAuthenticationError(AuthenticationMessages.WITHOUTTOKEN.toString()));
		} else if (!jwtTokenUtil.isValidToken(token.get())) {
			response.addError(Messages.getAuthenticationError(AuthenticationMessages.INVALIDTOKEN.toString()));
		}

		if (!response.getErrors().isEmpty()) {
			return ResponseEntity.badRequest().body(response);
		}

		String refreshedToken = jwtTokenUtil.refreshToken(token.get());

		Token newToken = new Token();
		newToken.setToken(refreshedToken);

		response.setData(newToken);
		return ResponseEntity.ok(response);
	}
}