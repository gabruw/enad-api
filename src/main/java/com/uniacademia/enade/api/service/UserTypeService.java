package com.uniacademia.enade.api.service;

import java.util.Optional;

import com.uniacademia.enade.api.entity.UserType;

public interface UserTypeService {

	Optional<UserType> findById(Long id);

	void deleteById(Long id);

	UserType persistir(UserType userType);
}