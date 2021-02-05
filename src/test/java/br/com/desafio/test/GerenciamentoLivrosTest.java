package br.com.desafio.test;

import org.junit.Test;

import br.com.desafio.dao.DaoGeneric;
import br.com.desafio.model.Livros;

public class GerenciamentoLivrosTest {

	@Test
	public void pesquisarLivrosTest() {
		DaoGeneric<Livros> daoGeneric = new DaoGeneric<Livros>();
		Livros livros = new Livros();
		livros.setIsbn(59L);
		
		livros =  daoGeneric.pesquisar(livros);
		System.out.println(livros);
	}
	
	
	@Test
	public void pesquisarLivrosTest2() {
		DaoGeneric<Livros> daoGeneric = new DaoGeneric<Livros>();
		Livros pessoa =  daoGeneric.pesquisar(59L, Livros.class);
		System.out.println(pessoa);
	}
	
	
	@Test
	public void testandoAlteracaoLivros() {
		DaoGeneric<Livros> daoGeneric = new DaoGeneric<Livros>();
		Livros livros =  daoGeneric.pesquisar(59L, Livros.class);
		
		livros.setTitulo("Testando a atualização (fiz somente com o Titulo mesmo)");
		livros = daoGeneric.updateMerge(livros);
		
		System.out.println(livros);
	}
	
	
	@Test
	public void testeDeleteLivro() throws Exception {
		DaoGeneric<Livros> daoGeneric = new DaoGeneric<Livros>();
		Livros livros =  daoGeneric.pesquisar(59L, Livros.class);
		daoGeneric.deletarPorIsbn(livros.getIsbn());
	}
	
	/*Faltou alguns teste para se fazer, pela falta de tempo!*/

	
}
