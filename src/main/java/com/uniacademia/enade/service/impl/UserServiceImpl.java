package com.uniacademia.enade.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.entity.User;
import com.uniacademia.enade.repository.UserRepository;
import com.uniacademia.enade.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		log.info("Buscando um Tipo de Usuário por Id {}", id);
		return userRepository.findById(id);
	}

	@Override
	public User persistir(User user) {
		log.info("Persistindo Usuário: {}", user);
		return this.userRepository.save(user);
	}
}
