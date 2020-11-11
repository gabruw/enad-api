package com.uniacademia.enade.service;

import java.util.Optional;

import com.uniacademia.enade.entity.Authentication;

public interface AuthenticationService {
	Optional<Authentication> findByEmail(String email);
	Authentication persistir(Authentication authentication);
}
