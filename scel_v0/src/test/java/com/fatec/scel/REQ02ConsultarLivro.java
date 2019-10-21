package com.fatec.scel;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class REQ02ConsultarLivro {
	@Autowired
	LivroRepository repository ;
	static Livro livro;  
	@Test
	public void test() {
		// dado que nao existem livros cadastrados
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// o sistema valida as informações E envia uma mensagem de aluno cadastrado com sucesso
		
		Livro ro = repository.findByIsbn("3333");
		assertEquals("Delamaro", ro.getAutor());
	}
}
