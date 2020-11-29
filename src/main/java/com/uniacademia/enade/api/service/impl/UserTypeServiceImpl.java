package com.uniacademia.enade.api.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uniacademia.enade.api.dto.Option;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.repository.UserTypeRepository;
import com.uniacademia.enade.api.service.UserTypeService;

@Service
public class UserTypeServiceImpl implements UserTypeService {

	private static final Logger log = LoggerFactory.getLogger(UserTypeServiceImpl.class);

	@Autowired
	private UserTypeRepository userTypeRepository;

	@Override
	public List<Option> findOptions() {
		log.info("Buscando todas as opções dos tipos de usuários");

		List<UserType> userTypes = userTypeRepository.findAll();
		return userTypes.stream().map(userType -> {
			Option opt = new Option();
			opt.setValue(userType.getId());
			opt.setText(userType.getName());
			return opt;
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<UserType> findById(Long id) {
		log.info("Buscando um tipo de usuário pelo 'Id' {}", id);
		return userTypeRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		log.info("Removendo tipo de usuário pelo 'Id' {}", id);
		userTypeRepository.deleteById(id);
	}

	@Override
	public UserType persistir(UserType userType) {
		log.info("Persistindo tipo de usuário: {}", userType);
		return this.userTypeRepository.save(userType);
	}

	@Override
	public Optional<UserType> findByName(String name) {
		log.info("Buscando um tipo de usuário pelo 'Nome' {}", name);
		return userTypeRepository.findByName(name);
	}

	@Override
	public Page<UserType> findAll(PageRequest pageRequest) {
		log.info("Buscando todos os tipos de usuário paginados");
		return userTypeRepository.findAll(pageRequest);
	}
}
