package com.uniacademia.enade.api.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.EditSubject;
import com.uniacademia.enade.api.dto.IncludeSubject;
import com.uniacademia.enade.api.entity.Subject;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.SubjectService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@RequestMapping("/subject")
public class SubjectController {
	private static final Logger log = LoggerFactory.getLogger(SubjectController.class);

	@Value("${paginacao.size.default}")
	private int pageSize;

	@Autowired
	private SubjectService subjectService;

	@GetMapping("/find-all")
	public ResponseEntity<Response<Page<Subject>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction)
			throws NoSuchAlgorithmException {
		Response<Page<Subject>> response = new Response<Page<Subject>>();

		PageRequest pageRequest = PageRequest.of(page, this.pageSize, Direction.valueOf(direction), order);
		Page<Subject> subjects = subjectService.findAll(pageRequest);
		response.setData(subjects);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Response<Subject>> find(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		Response<Subject> response = new Response<Subject>();

		Optional<Subject> subject = subjectService.findById(id);
		if (!subject.isPresent()) {
			log.info("Erro ao validar o 'Id' para busca do assunto: {}", id);
			response.addError(Messages.getCategoriaError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(subject.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Subject>> include(@Valid @RequestBody IncludeSubject includeSubject,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Iniciando cadastro do assunto: {}", includeSubject.toString());
		Response<Subject> response = new Response<Subject>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro do assunto: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Subject subject = IncludeSubject.buildIncludeSubject(includeSubject);
		subject = this.subjectService.persistir(subject);

		response.setData(subject);
		return ResponseEntity.ok(response);
	}

	@PatchMapping("/edit")
	public ResponseEntity<Response<Subject>> edit(@Valid @RequestBody EditSubject editSubject, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Iniciando edição da categoria: {}", editSubject.toString());
		Response<Subject> response = new Response<Subject>();

		if (result.hasErrors()) {
			log.error("Erro validando dados para edição do assunto: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Subject subject = EditSubject.buildEditSubject(editSubject);
		subject = this.subjectService.persistir(subject);

		response.setData(subject);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Response<Subject>> remove(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		log.info("Iniciando remoção da categoria: {}", id);
		Response<Subject> response = new Response<Subject>();

		Optional<Subject> subject = this.subjectService.findById(id);
		if (!subject.isPresent()) {
			log.info("Erro ao validar o 'Id' para remoção do assunto: {}", id);
			response.addError(Messages.getCategoriaError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		this.subjectService.deleteById(id);
		response.setData(subject.get());

		return ResponseEntity.ok(response);
	}
}
