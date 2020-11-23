package com.uniacademia.enade.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.entity.User;
import com.uniacademia.enade.api.repository.UserRepository;
import com.uniacademia.enade.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		log.info("Buscando um usuário pelo 'Id' {}", id);
		return this.userRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo um usuário pelo 'Id' {}", id);
		this.userRepository.deleteById(id);
	}

	@Override
	public User persistir(User user) {
		log.info("Persistindo usuário: {}", user);
		return this.userRepository.save(user);
	}
}