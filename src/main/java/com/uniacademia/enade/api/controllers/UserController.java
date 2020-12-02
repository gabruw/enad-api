package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.EditRegister;
import com.uniacademia.enade.api.dto.IncludeRegister;
import com.uniacademia.enade.api.dto.Login;
import com.uniacademia.enade.api.entity.Authentication;
import com.uniacademia.enade.api.entity.User;
import com.uniacademia.enade.api.entity.UserType;
import com.uniacademia.enade.api.enumerator.AuthenticationMessages;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.enumerator.UserMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.AuthenticationService;
import com.uniacademia.enade.api.service.UserService;
import com.uniacademia.enade.api.service.UserTypeService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	private static final String DEFAULT_ROLE = "Aluno";

	@Autowired
	private UserService userService;

	@Autowired
	private UserTypeService userTypeService;

	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/include")
	public ResponseEntity<Response<IncludeRegister>> include(@Valid @RequestBody IncludeRegister includeRegister,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Usuário: {}", includeRegister.toString());
		Response<IncludeRegister> response = new Response<IncludeRegister>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para cadastro do Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<User> checkCpf = this.userService.findByCpf(includeRegister.getUser().getCpf());
		if (checkCpf.isPresent()) {
			log.error("Erro ao validar o CPF: {}", checkCpf.get().getCpf());
			response.addError(Messages.getUserError(UserMessages.ALREADYEXISTSCPF.toString()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Authentication> checkEmail = this.authenticationService
				.findByEmail(includeRegister.getAuthentication().getEmail());
		if (checkCpf.isPresent()) {
			log.error("Erro ao validar o Email: {}", checkEmail.get().getEmail());
			response.addError(Messages.getAuthenticationError(AuthenticationMessages.ALREADYEXISTSEMAIL.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findByName(DEFAULT_ROLE);
		if (!userType.isPresent()) {
			log.error("Erro ao validar o Tipo de Usuário");
			response.addError(Messages.getUserTypeError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = Login.buildAuthentication(includeRegister.getAuthentication());
		User user = IncludeRegister.buildIncludeRegister(includeRegister.getUser(), authentication);
		user.setUserType(userType.get());

		String encodedPassword = new BCryptPasswordEncoder().encode(authentication.getPassword());
		authentication.setPassword(encodedPassword);

		authentication.setUser(user);
		authentication = this.authenticationService.persistir(authentication);

		response.setData(includeRegister);
		return ResponseEntity.ok(response);
	}

	@PutMapping("/edit")
	public ResponseEntity<Response<User>> edit(@Valid @RequestBody EditRegister editRegister, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Editando Usuário: {}", editRegister.toString());
		Response<User> response = new Response<User>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição do Usuário: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Optional<UserType> userType = this.userTypeService.findByName(DEFAULT_ROLE);
		if (!userType.isPresent()) {
			log.error("Erro ao validar o Tipo de Usuário");
			response.addError(Messages.getUserTypeError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		Authentication authentication = Login.buildAuthentication(editRegister.getAuthentication());
		authentication = this.authenticationService.persistir(authentication);

		User user = EditRegister.buildEditRegister(editRegister.getUser(), authentication);
		user.setUserType(userType.get());
		user = this.userService.persistir(user);

		response.setData(user);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response<User>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Removendo Usuário: {}", id);
		Response<User> response = new Response<User>();

		Optional<User> user = this.userService.findById(id);
		if (!user.isPresent()) {
			log.info("Autenticação não encontrada para o Email: {}", id);
			response.addError(Messages.getUserError(GenericMessages.NONEXISTENT.toString(), id));

			return ResponseEntity.badRequest().body(response);
		}

		this.authenticationService.deleteById(user.get().getAuthentication().getId());
		this.userService.deleteById(id);

		response.setData(user.get());
		return ResponseEntity.ok(response);
	}
}