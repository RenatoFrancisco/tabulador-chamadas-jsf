package br.com.square.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	@OneToMany(mappedBy = "produto")
	private List<Tabulacao> tabulacoes;

	@ManyToMany
	private List<Arvore> arvores = new ArrayList<Arvore>();

	public List<Tabulacao> getTabulacoes() {
		return tabulacoes;
	}

	public void setTabulacoes(List<Tabulacao> tabulacoes) {
		this.tabulacoes = tabulacoes;
	}

	public List<Arvore> getArvores() {
		return arvores;
	}

	public void setArvores(List<Arvore> arvores) {
		this.arvores = arvores;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void addArvore(Arvore arvore) {
		this.arvores.add(arvore);
		arvore.getProdutos().add(this);
	}
}