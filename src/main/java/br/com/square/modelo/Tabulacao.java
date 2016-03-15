package br.com.square.modelo;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.square.enums.Rechamada;

@Entity
public class Tabulacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "{tabulacao.terminal.notempty}")
	private String terminal;

	@Temporal(TemporalType.DATE)
	@NotEmpty(message = "{tabulacao.datachamada.notempty}")
	@Past(message = "{tabulacao.datachamada.past}")
	private Calendar dataChamada = Calendar.getInstance();

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataTabulacao = Calendar.getInstance();
	
	@Size(max = 100, message = "{tabulacao.descricao.size}")
	private String descricao;

	@ManyToOne
	Produto produto;

	@ManyToOne
	private Arvore arvore;

	@ManyToOne
	private Usuario usuario;

	@Enumerated(EnumType.STRING)
	private Rechamada rechamada;

	@ManyToOne
	private Site site;

	public Calendar getDataTabulacao() {
		return dataTabulacao;
	}

	public void setDataTabulacao(Calendar dataTabulacao) {
		this.dataTabulacao = dataTabulacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public Calendar getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(Calendar dataChamada) {
		this.dataChamada = dataChamada;
	}

	public Arvore getArvore() {
		return arvore;
	}

	public void setArvore(Arvore arvore) {
		this.arvore = arvore;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Rechamada getRechamada() {
		return rechamada;
	}

	public void setRechamada(Rechamada rechamada) {
		this.rechamada = rechamada;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
}
