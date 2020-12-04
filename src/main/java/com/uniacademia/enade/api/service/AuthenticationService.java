package com.uniacademia.enade.api.service;

import java.util.Optional;

import com.uniacademia.enade.api.entity.Authentication;

public interface AuthenticationService {
	Optional<Authentication> findByEmail(String email);

	void deleteById(Long id);
	
	Authentication persistir(Authentication authentication);
}
