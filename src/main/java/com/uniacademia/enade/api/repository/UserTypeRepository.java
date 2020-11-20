package com.uniacademia.enade.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	Optional<UserType> findById(Long id);
}