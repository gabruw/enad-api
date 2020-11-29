package com.uniacademia.enade.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.api.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	@Transactional
	void deleteById(Long id);

	@Transactional(readOnly = true)
	List<UserType> findAll();

	@Transactional(readOnly = true)
	Optional<UserType> findById(Long id);

	@Transactional(readOnly = true)
	Optional<UserType> findByName(String name);

	@Transactional(readOnly = true)
	Page<UserType> findAll(Pageable pageable);
}
