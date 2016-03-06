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
public class Arvore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String motivo;

	private String submotivo;

	private String detalhe;

	private boolean ativo;

	@OneToMany(mappedBy = "arvore")
	private List<Tabulacao> tabulacoes;

	@ManyToMany(mappedBy = "arvores")
	private List<Produto> produtos = new ArrayList<Produto>();

	public List<Tabulacao> getTabulacoes() {
		return tabulacoes;
	}

	public void setTabulacoes(List<Tabulacao> tabulacoes) {
		this.tabulacoes = tabulacoes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getSubmotivo() {
		return submotivo;
	}

	public void setSubmotivo(String submotivo) {
		this.submotivo = submotivo;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
