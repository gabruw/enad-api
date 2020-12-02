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

import com.uniacademia.enade.api.dto.IncludeQuestion;
import com.uniacademia.enade.api.entity.Question;
import com.uniacademia.enade.api.enumerator.GenericMessages;
import com.uniacademia.enade.api.response.Response;
import com.uniacademia.enade.api.service.QuestionService;
import com.uniacademia.enade.api.utils.Messages;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/question")
public class QuestionController {
	private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

	@Value("${paginacao.size.default}")
	private int pageSize;

	@Autowired
	private QuestionService questionService;

	@GetMapping("/find-all")
	public ResponseEntity<Response<Page<Question>>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "order", defaultValue = "id") String order,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction)
			throws NoSuchAlgorithmException {
		Response<Page<Question>> response = new Response<Page<Question>>();

		PageRequest pageRequest = PageRequest.of(page, this.pageSize, Direction.valueOf(direction), order);
		Page<Question> questions = questionService.findAll(pageRequest);
		response.setData(questions);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Response<Question>> find(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		Response<Question> response = new Response<Question>();

		Optional<Question> question = questionService.findById(id);
		if (!question.isPresent()) {
			log.info("Erro ao validar o 'Id' para busca da pergunta: {}", id);
			response.addError(Messages.getQuestionError(GenericMessages.NONEXISTENT.toString()));

			return ResponseEntity.badRequest().body(response);
		}

		response.setData(question.get());
		return ResponseEntity.ok(response);
	}

	@PostMapping("/include")
	public ResponseEntity<Response<Question>> include(@Valid @RequestBody IncludeQuestion includeQuestion,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Iniciando cadastro da pergunta: {}", includeQuestion.toString());
		Response<Question> response = new Response<Question>();

		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro da pergunta: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.addFieldError(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		Question question = IncludeQuestion.buildIncludeQuestion(includeQuestion);
		question = this.questionService.persistir(question);

		response.setData(question);
		return ResponseEntity.ok(response);
	}
}
