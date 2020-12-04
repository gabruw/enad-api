package com.uniacademia.enade.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	Optional<User> findById(Long id);

	@Transactional(readOnly = true)
	Optional<User> findByCpf(String cpf);
}
