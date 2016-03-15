package br.com.square.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.square.dao.PerfilDao;
import br.com.square.dao.SiteDao;
import br.com.square.dao.UsuarioDao;
import br.com.square.modelo.Perfil;
import br.com.square.modelo.Site;
import br.com.square.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	
	@NotEmpty(message = "Perfil deve ser preenchido.")
	private String perfilNome;
	
	@NotEmpty(message = "Site deve ser preenchido.")
	private String siteSigla;
	
	private List<Usuario> usuarios;
	private List<Perfil> perfis;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private PerfilDao perfilDao;

	@Inject
	private SiteDao siteDao;

	public void salva() {
		System.out.println("Sigla do site " + this.siteSigla);
		Perfil perfil = this.perfilDao.buscaPorNome(this.perfilNome);
		Site site = this.siteDao.buscaPorSigla(this.siteSigla);
		insereRelacionamentos(perfil, site);

		if (this.usuario.getId() == 0L) {
			this.usuarioDao.adiciona(this.usuario);
		} else {
			this.usuarioDao.atualiza(usuario);
		}

		this.limpa();
		this.listaTodos();
	}

	private void insereRelacionamentos(Perfil perfil, Site site) {
		this.usuario.setPerfil(perfil);
		this.usuario.setSite(site);
	}

	public void remove(Usuario usuario) {
		this.usuarioDao.remove(usuario);
		this.listaTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getPerfilNome() {
		return perfilNome;
	}

	public void setPerfilNome(String perfilNome) {
		this.perfilNome = perfilNome;
	}

	public List<Usuario> getUsuarios() {
		if (this.usuarios == null) {
			this.listaTodos();
		}
		return usuarios;
	}

	public List<Perfil> getPerfis() {
		if (this.perfis == null) {
			this.perfis = this.perfilDao.lista();
		}
		return perfis;
	}
	
	public String getSiteSigla() {
		return siteSigla;
	}
	
	public void setSiteSigla(String siteSigla) {
		this.siteSigla = siteSigla;
	}
		
	private void limpa() {
		this.usuario = new Usuario();
		this.perfilNome = null;
		this.siteSigla = null;
	}

	private void listaTodos() {
		this.usuarios = usuarioDao.lista();
	}


}
