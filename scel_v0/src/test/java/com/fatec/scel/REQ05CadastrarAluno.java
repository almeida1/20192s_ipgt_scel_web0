package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.fatec.scel.model.Aluno;
import com.fatec.scel.model.AlunoRepository;


class REQ05CadastrarAluno {
    AlunoRepository repository = new AlunoRepository();
   
	@Test
	public void test() {
		// dado que o aluno nao esta cadastrado
		repository.deleteAll();
		// quando o usurio inclui as informacoes obrigatorias e confirma a operacao
		Aluno aluno = new Aluno();
		repository.save(aluno);
		// o sistema valida as informações E envia uma mensagem de livro cadastrado com sucesso
		assertEquals(1, repository.count());
	}

}
