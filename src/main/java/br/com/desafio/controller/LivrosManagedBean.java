package br.com.desafio.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.desafio.dao.DaoAutores;
import br.com.desafio.dao.DaoCriticas;
import br.com.desafio.dao.DaoLivros;
import br.com.desafio.carregamento.LazyDataTableLivros;
import br.com.desafio.model.Autores;
import br.com.desafio.model.Criticas;
import br.com.desafio.model.Livros;
import br.com.desafio.converterExtenso.ConvertePrecoExtenso;

@ManagedBean(name = "livrosManagedBean")
@ViewScoped
public class LivrosManagedBean {

	private Livros livros = new Livros();
	private LazyDataTableLivros<Livros> list = new LazyDataTableLivros<Livros>();
	private DaoLivros<Livros> daoLivros = new DaoLivros<Livros>();
	private Autores autores = new Autores();
	private DaoAutores<Autores> daoAutores = new DaoAutores<Autores>();
	private Criticas criticas = new Criticas();
	private DaoCriticas<Criticas> daoCriticas = new DaoCriticas<Criticas>();
	private String campoPesquisa;

	@PostConstruct
	public void init() {
		list.load(0, 5, null, null, null);
		// livros.setPreco(Double.valueOf(new ConvertePrecoExtenso().toString()));
	}

	public String salvar() {
		daoLivros.updateMerge(livros);
		list.list.add(livros);
		livros = new Livros();
		init();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Livro Cadastrado com Sucesso!"));
		return "";
	}

	public String remover() {
		try {
			daoLivros.removerLivros(livros);
			list.list.remove(livros);
			livros = new Livros();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Livro Removido com Sucesso!"));
			retornaCadastroLivros();
			init();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erro: ",
					"Erro ao tentar removar o Livro: " + livros.getTitulo()));
			e.printStackTrace();
		}
		return "gerenciamento_livros.jsf?faces-redirect=true";
	}

	public void addAutor() {
		autores.setLivros(livros);
		autores = daoAutores.updateMerge(autores);
		livros.getAutores().add(autores);
		autores = new Autores();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Autor Cadastrado!"));
	}

	public void addCritica() {
		criticas.setLivros(livros);
		criticas = daoCriticas.updateMerge(criticas);
		livros.getCriticas().add(criticas);
		criticas = new Criticas();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Critica Cadastrado!"));
	}

	public void removerAutor() throws Exception {
		String codigoautor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigoautor");
		Autores remover = new Autores();
		remover.setId(Long.parseLong(codigoautor));
		daoAutores.deletarPorId(remover);
		livros.getAutores().remove(remover);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Autor Removido com Sucesso!"));
	}

	public void removerCritica() throws Exception {
		String codigocritica = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigocritica");
		Criticas remover = new Criticas();
		remover.setId(Long.parseLong(codigocritica));
		daoCriticas.deletarPorId(remover);
		livros.getCriticas().remove(remover);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Critica Removido com Sucesso!"));
	}

	public void pesquisarListaLivros() {
		list.pesquisa(campoPesquisa);
	}

	public Livros getLivros() {
		return livros;
	}

	public void setLivros(Livros livros) {
		this.livros = livros;
	}

	public String novo() {
		livros = new Livros();
		return "";
	}

	public LazyDataTableLivros<Livros> getList() {
		return list;
	}

	public void setAutores(Autores autores) {
		this.autores = autores;
	}

	public Autores getAutores() {
		return autores;
	}

	public void setCriticas(Criticas criticas) {
		this.criticas = criticas;
	}

	public Criticas getCriticas() {
		return criticas;
	}

	public void setCampoPesquisa(String campoPesquisa) {
		this.campoPesquisa = campoPesquisa;
	}

	public String getCampoPesquisa() {
		return campoPesquisa;
	}

	private String retornaCadastroLivros() {
		return "gerenciamento_livros.jsf?faces-redirect=true";
	}

}
