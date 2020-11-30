package com.uniacademia.enade.api.service;

import java.util.Optional;

import com.uniacademia.enade.api.entity.User;

public interface UserService {
	void deleteById(Long id);

	User persistir(User user);

	Optional<User> findById(Long id);

	Optional<User> findByCpf(String cpf);
}
