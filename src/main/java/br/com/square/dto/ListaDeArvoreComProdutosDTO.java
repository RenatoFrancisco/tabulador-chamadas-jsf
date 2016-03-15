package br.com.square.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ListaDeArvoreComProdutosDTO {
	
	private String produto;

	@NotEmpty(message = "{arvore.motivo.notempty}")
	@Size(min = 3, max = 20, message = "{arvore.motivo.size}")
	private String motivo;
	
	@NotEmpty(message = "{arvore.submotivo.notempty}")
	@Size(min = 3, max = 20, message = "{arvore.submotivo.size}")
	private String submotivo;
	
	@NotEmpty(message = "{arvore.detalhe.notempty}")
	@Size(min = 3, max = 20, message = "{arvore.detalhe.size}")
	private String detalhe;
	
	private boolean ativo;
	private long idArvore;

	public ListaDeArvoreComProdutosDTO(String produto, String motivo,
			String submotivo, String detalhe, boolean ativo, long idArvore) {
		this.produto = produto;
		this.motivo = motivo;
		this.submotivo = submotivo;
		this.detalhe = detalhe;
		this.ativo = ativo;
		this.idArvore = idArvore;
	}
	
	public ListaDeArvoreComProdutosDTO() {}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
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

	public long getIdArvore() {
		return idArvore;
	}

	public void setIdArvore(long idArvore) {
		this.idArvore = idArvore;
	}

}
