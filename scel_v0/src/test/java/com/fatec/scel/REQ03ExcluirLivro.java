package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
class REQ03ExcluirLivro {

	@Autowired
	LivroRepository repository;
    /**
     * Verificar o comportamento da classe LivroRepository
     */
	@Test
	public void CT01ExcluirLivroComSucesso() {
		// dado que o isbn nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		Livro ro = repository.findByIsbn("3333");
		repository.deleteById(ro.getId());
		// entao o sistema valida as informações E envia uma mensagem de livro cadastrado com sucesso
		assertThat(repository.findByIsbn("3333")).isEqualTo(null);
	}
	

}
