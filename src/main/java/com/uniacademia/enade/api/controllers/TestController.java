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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uniacademia.enade.api.dto.IncludeTest;
import com.uniacademia.enade.api.entity.Test;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.TestService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/test")
public class TestController {
	private static final Logger log = LoggerFactory.getLogger(TestController.class);

	@Value("${paginacao.size.default}")
	private int pageSize;

	@Autowired
	private TestService testService;

	@GetMapping("/find-all")
	public ResponseEntity<Response<Page<Test>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction)
			throws NoSuchAlgorithmException {
		Response<Page<Test>> response = new Response<Page<Test>>();

		PageRequest pageRequest = PageRequest.of(page, this.pageSize, Direction.valueOf(direction), order);
		Page<Test> tests = testService.findAll(pageRequest);
		response.setData(tests);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Response<Test>> find(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		Response<Test> response = new Response<Test>();

		Optional<Test> question = testService.findById(id);
		if (!question.isPresent()) {
			log.info("Erro ao validar o 'Id' para busca da prova: {}", id);
			response.addError(Messages.getTestError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(question.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Test>> include(@Valid @RequestBody IncludeTest includeTest, BindingResult result)
			throws NoSuchAlgorithmException {
		log.info("Iniciando cadastro da prova: {}", includeTest.toString());
		Response<Test> response = new Response<Test>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro da prova: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Test test = IncludeTest.buildIncludeTest(includeTest);
		test = this.testService.persistir(test);

		response.setData(test);
		return ResponseEntity.ok(response);
	}
}
