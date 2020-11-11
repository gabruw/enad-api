package com.uniacademia.enade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enade.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
	
	@Transactional(readOnly = true)
	Authentication findByEmail(String email);

}
