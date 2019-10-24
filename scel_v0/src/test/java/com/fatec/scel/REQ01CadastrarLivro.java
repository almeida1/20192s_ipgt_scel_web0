package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ01CadastrarLivro {

	@Autowired
	LivroRepository repository;

	private Validator validator;
	private ValidatorFactory validatorFactory;
	
	@Test
	public void CT01CadastrarLivroComSucesso_dados_validos() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// given:
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");

		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);

		// then:
		assertThat(violations.isEmpty()).isEqualTo(true);
		
	}
	/**
	 * Verificar o comportamento da classe LivroRepository
	 */
	@Test
	public void CT02CadastrarLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// entao
		assertThat(repository.count()).isEqualTo(1);
		
	}

	

	
	@Test
	public void CT03DeveDetectarTituloInvalido() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
		// dado que o titulo do livro esta invalido
		Livro livro = new Livro("3333", "", "Delamaro");

		// when:
		Set<ConstraintViolation<Livro>> violations = validator.validate(livro);

		// then:
		assertEquals(violations.size(), 1);
		assertEquals("O titulo deve ser preenchido", violations.iterator().next().getMessage());

	}
	
}
