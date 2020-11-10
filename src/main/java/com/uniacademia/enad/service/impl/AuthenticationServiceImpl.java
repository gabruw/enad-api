package com.uniacademia.enad.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enad.entity.Authentication;
import com.uniacademia.enad.repository.AuthenticationRepository;
import com.uniacademia.enad.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public Optional<Authentication> findByEmail(String email) {
		log.info("Buscando uma autenticação para o Email {}", email);
		return Optional.ofNullable(authenticationRepository.findByEmail(email));
	}

	@Override
	public Authentication persistir(Authentication authentication) {
		log.info("Persistindo autenticação: {}", authentication);
		return this.authenticationRepository.save(authentication);
	}
}
