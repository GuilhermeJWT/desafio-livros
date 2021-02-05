package br.com.desafio.carregamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.desafio.dao.DaoLivros;
import br.com.desafio.model.Livros;

public class LazyDataTableLivros<T> extends LazyDataModel<Livros> {

	private static final long serialVersionUID = 1L;

	private DaoLivros<Livros> dao = new DaoLivros<Livros>();
	public List<Livros> list = new ArrayList<Livros>();
	private String sql = " from Livros ";

	@Override
	public List<Livros> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		list = dao.getEntityManager().createQuery(getSql()).setFirstResult(first).setMaxResults(pageSize)
				.getResultList();

		sql = " from Livros";

		setPageSize(pageSize);
		Integer qtdRegistro = Integer.parseInt(
				dao.getEntityManager().createQuery("select count(1) " + getSql()).getSingleResult().toString());
		setRowCount(qtdRegistro);
		return list;

	}

	public String getSql() {
		return sql;
	}

	public List<Livros> getList() {
		return list;
	}

	public void pesquisa(String campoPesquisa) {

		sql += " where isbn = " + campoPesquisa + "";

		/* Sql para realizar a pesquisa conforme o Titulo: */
		/* sql += " where titulo like '%"+campoPesquisa+"%'"; */
	}

}
