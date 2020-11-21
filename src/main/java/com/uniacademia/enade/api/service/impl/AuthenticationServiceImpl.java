package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.repository.AuthenticationRepository;
import com.uniacademia.enade.api.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public Optional<Authentication> findByEmail(String email) {
		log.info("Buscando uma autenticação para o Email {}", email);
		return Optional.ofNullable(this.authenticationRepository.findByEmail(email));
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo autenticação por Id {}", id);
		this.authenticationRepository.deleteById(id);
	}

	@Override
	public Authentication persistir(Authentication authentication) {
		log.info("Persistindo autenticação: {}", authentication);
		return this.authenticationRepository.save(authentication);
	}
}
