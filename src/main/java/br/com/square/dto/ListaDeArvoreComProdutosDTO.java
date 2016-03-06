package br.com.square.dto;

public class ListaDeArvoreComProdutosDTO {
	
	private String produto;
	private String motivo;
	private String submotivo;
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
