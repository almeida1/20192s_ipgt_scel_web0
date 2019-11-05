package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

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
	public void CT01ConsultaTodos() {
		// dado que existem livros disponíveis para consulta na biblioteca
		repository.deleteAll();
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// quando o usurio solicita consultar todos
		Iterable<Livro> livros;
		livros = repository.findAll();
		//entao o sistema apresenta os livros disponiveis
		for (Iterator i = livros.iterator(); i.hasNext();) {
		       Livro livro = (Livro)i.next();
		}
		assertThat(livro.getIsbn()).isEqualTo("3333");
	}
	@Test
	public void CT02CosultaPorISBN() {
		// dado que existem livros disponíveis para consulta na biblioteca
		repository.deleteAll();
		livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.save(livro);
		// quando o usurio solicita consultar todos
		Iterable<Livro> livros;
		livros = repository.findAll();
		// entao o sistema valida as informações E envia uma mensagem de aluno cadastrado com sucesso
		Livro ro = repository.findByIsbn("3333");
		assertThat(ro).isEqualTo(livro);
	}
}
