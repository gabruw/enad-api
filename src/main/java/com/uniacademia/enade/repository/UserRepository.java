package com.uniacademia.enade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	Optional<User> findById(Long id);

}
