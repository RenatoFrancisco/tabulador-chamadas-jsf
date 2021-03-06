package br.com.square.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "{site.nome.notempty}")
	@Size(min = 3, max = 20, message = "{site.nome.size}")
	private String nome;

	@NotEmpty(message = "{site.sigla.notempty}")
	@Size(min = 2, max = 3, message = "{site.sigla.size}")
	private String sigla;

	private boolean ativo;

	@OneToMany(mappedBy = "site")
	private List<Usuario> usuarios;

	@OneToMany(mappedBy = "site")
	private List<Tabulacao> tabulacoes;

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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tabulacao> getTabulacoes() {
		return tabulacoes;
	}

	public void setTabulacoes(List<Tabulacao> tabulacoes) {
		this.tabulacoes = tabulacoes;
	}

}
