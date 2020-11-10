package com.uniacademia.enad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.uniacademia.enad.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Long> {
	
	@Transactional(readOnly = true)
	Authentication findByEmail(String email);

}
