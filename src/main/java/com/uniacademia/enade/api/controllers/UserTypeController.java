package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.EditUserType;
import com.uniacademia.enade.api.dto.IncludeUserType;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.UserTypeService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/user-type")
public class UserTypeController {
	private static final Logger log = LoggerFactory.getLogger(UserTypeController.class);

	@Autowired
	private UserTypeService userTypeService;

	@PostMapping("/include")
	public ResponseEntity<Response<UserType>> include(@Valid @RequestBody IncludeUserType includeUserType,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Iniciando cadastro do tipo de usuário: {}", includeUserType.toString());
		Response<UserType> response = new Response<UserType>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro de tipo de usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> optUserType = this.userTypeService.findByName(includeUserType.getName());
		if (optUserType.isPresent()) {
			log.info("Erro ao validar o 'Nome' do tipo de usuário: {}", optUserType.get().getName());
			response.addError(Messages.getUserTypeError(GenericMessages.ALREADYEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		UserType userType = IncludeUserType.buildIncludeUserType(includeUserType);
		this.userTypeService.persistir(userType);

		response.setData(userType);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/edit")
	public ResponseEntity<Response<UserType>> edit(@Valid @RequestBody EditUserType editUserType, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Iniciando edição do tipo de usuário: {}", editUserType.toString());
		Response<UserType> response = new Response<UserType>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição de tipo de usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> optUserType = this.userTypeService.findByName(editUserType.getName());
		if (optUserType.isPresent()) {
			log.info("Erro ao validar o 'Nome' do tipo de usuário: {}", optUserType.get().getName());
			response.addError(Messages.getUserTypeError(GenericMessages.ALREADYEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		UserType userType = EditUserType.buildIncludeUserType(editUserType);
		this.userTypeService.persistir(userType);

		response.setData(userType);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response<UserType>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Iniciando remoção do tipo de usuário: {}", id);
		Response<UserType> response = new Response<UserType>();

		Optional<UserType> userType = this.userTypeService.findById(id);
		if (userType.isEmpty()) {
			log.info("Erro ao validar o 'Id' para remoção do tipo de usuário: {}", id);
			response.addError(Messages.getUserTypeError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		this.userTypeService.deleteById(id);
		response.setData(userType.get());

		return ResponseEntity.ok(response);
	}
}
