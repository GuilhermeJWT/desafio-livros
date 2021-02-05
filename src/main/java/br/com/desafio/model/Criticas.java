package br.com.desafio.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Criticas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String descricao_critica;
	
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Livros livros_critica;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao_critica() {
		return descricao_critica;
	}

	public void setDescricao_critica(String descricao_critica) {
		this.descricao_critica = descricao_critica;
	}

	public Livros getLivros() {
		return livros_critica;
	}

	public void setLivros(Livros livros) {
		this.livros_critica = livros;
	}

}
