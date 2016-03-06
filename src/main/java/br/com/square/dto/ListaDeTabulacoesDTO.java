package br.com.square.dto;

import java.util.Calendar;

public class ListaDeTabulacoesDTO {

	private Calendar dataChamada;
	private String terminal;
	private String produto;
	private String motivo;
	private String submotivo;
	private String detalhe;
	private String descricao;
	private String nomeUsuario;
	private Calendar dataTabulacao;

	public ListaDeTabulacoesDTO(Calendar dataChamada, String terminal,
			String produto, String motivo, String submotivo, String detalhe,
			String descricao, String nomeUsuario, Calendar dataTabulacao) {

		this.dataChamada = dataChamada;
		this.terminal = terminal;
		this.produto = produto;
		this.motivo = motivo;
		this.submotivo = submotivo;
		this.detalhe = detalhe;
		this.descricao = descricao;
		this.setNomeUsuario(nomeUsuario);
		this.dataTabulacao = dataTabulacao;
	}

	public Calendar getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(Calendar dataChamada) {
		this.dataChamada = dataChamada;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataTabulacao() {
		return dataTabulacao;
	}

	public void setDataTabulacao(Calendar dataTabulacao) {
		this.dataTabulacao = dataTabulacao;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
