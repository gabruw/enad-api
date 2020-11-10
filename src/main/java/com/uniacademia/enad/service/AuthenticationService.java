package com.uniacademia.enad.service;

import java.util.Optional;

import com.uniacademia.enad.entity.Authentication;

public interface AuthenticationService {
	Optional<Authentication> findByEmail(String email);
	Authentication persistir(Authentication authentication);
}
