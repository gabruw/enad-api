package com.uniacademia.enade.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.uniacademia.enade.api.dto.Option;
import com.uniacademia.enade.api.entity.UserType;

public interface UserTypeService {

	void deleteById(Long id);

	List<Option> findOptions();

	Optional<UserType> findById(Long id);

	UserType persistir(UserType userType);

	Optional<UserType> findByName(String name);

	Page<UserType> findAll(PageRequest pageRequest);
}