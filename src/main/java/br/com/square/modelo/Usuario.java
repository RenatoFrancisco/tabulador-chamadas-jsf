package br.com.square.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "{usuario.nome.notempty}")
	@Size(min = 5, message = "{usuario.nome.size}")
	private String nome;

	@Email(message = "{usuario.email.email}")
	@NotEmpty(message = "{usuario.email.notempty}")
	private String email;

	@NotEmpty(message = "{usuario.senha.notempty}")
	@Size(min = 6, max = 12, message = "{usuario.senha.size}")
	private String senha;

	@ManyToOne
	private Perfil perfil;

	@OneToMany(mappedBy = "usuario")
	List<Tabulacao> tabulacoes;

	@ManyToOne
	private Site site;

	public boolean isAdministrador() {
		return "Administrador".equals(this.perfil.getNome());
	}

	public List<Tabulacao> getTabulacoes() {
		return tabulacoes;
	}

	public void setTabulacoes(List<Tabulacao> tabulacoes) {
		this.tabulacoes = tabulacoes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

}
