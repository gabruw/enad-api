package com.uniacademia.enade.service;

import java.util.Optional;

import com.uniacademia.enade.entity.User;

public interface UserService {
	Optional<User> findById(Long id);

	User persistir(User user);
}
