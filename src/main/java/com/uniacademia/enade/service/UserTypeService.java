package com.uniacademia.enade.service;

import java.util.Optional;

import com.uniacademia.enade.entity.UserType;

public interface UserTypeService {
	Optional<UserType> findById(Long id);

	UserType persistir(UserType userType);
}