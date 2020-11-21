package com.uniacademia.enade.api.service;

import java.util.List;
import java.util.Optional;

import com.uniacademia.enade.api.entity.UserType;

public interface UserTypeService {

	void deleteById(Long id);
	
	List<UserType> findAll();
	
	Optional<UserType> findById(Long id);

	UserType persistir(UserType userType);

	Optional<UserType> findByName(String name);
}