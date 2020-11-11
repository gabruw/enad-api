package com.uniacademia.enade.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.entity.UserType;
import com.uniacademia.enade.repository.UserTypeRepository;
import com.uniacademia.enade.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	private static final Logger log = LoggerFactory.getLogger(UserTypeServiceImpl.class);

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public void deleteById(Long id) {
		log.info("Removendo Tipo de Usuário por Id {}", id);
		userTypeRepository.deleteById(id);
	}

	@Override
	public Optional<UserType> findById(Long id) {
		log.info("Buscando um Tipo de Usuário por Id {}", id);
		return userTypeRepository.findById(id);
	}

	@Override
	public UserType persistir(UserType userType) {
		log.info("Persistindo Tipo de Usuário: {}", userType);
		return this.userTypeRepository.save(userType);
	}
}
