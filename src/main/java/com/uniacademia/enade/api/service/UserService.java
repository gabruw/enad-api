package com.uniacademia.enade.api.service;

import java.util.Optional;

import com.uniacademia.enade.api.entity.User;

public interface UserService {
	Optional<User> findById(Long id);

	User persistir(User user);
}
