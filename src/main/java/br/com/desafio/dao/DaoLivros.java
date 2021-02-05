package br.com.desafio.dao;

import java.util.List;
import javax.persistence.Query;
import br.com.desafio.model.Livros;

public class DaoLivros<E> extends DaoGeneric<Livros> {

	public void removerLivros(Livros livros) throws Exception {
		getEntityManager().getTransaction().begin();
		String sql = "delete from autores where livros_isbn = " + livros.getIsbn();
		getEntityManager().createNativeQuery(sql).executeUpdate();

		sql = "delete from criticas where livros_critica_isbn = " + livros.getIsbn();
		getEntityManager().createNativeQuery(sql).executeUpdate();

		sql = "delete from livros where isbn = " + livros.getIsbn();
		getEntityManager().createNativeQuery(sql).executeUpdate();

		getEntityManager().getTransaction().commit();

	}

	public List<Livros> pesquisar(Long campoPesquisa) {
		Query query = super.getEntityManager().createQuery("from Livros where isbn like '%" + campoPesquisa + "%'");
		return query.getResultList();
	}

}
