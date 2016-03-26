package br.com.square.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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

	@Inject
	private UsuarioLogadoBean usuarioLogado;

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
		Perfil perfil = this.perfilDao.buscaPorNome(this.perfilNome);
		Site site = this.siteDao.buscaPorSigla(this.siteSigla);
		insereRelacionamentos(perfil, site);

		if (this.usuario.getId() == 0L) {
			this.usuarioDao.adiciona(this.usuario);
		} else {
			this.usuarioDao.atualiza(usuario);
		}

		this.addMessage("Usuário salvo com sucesso!", null);

		this.limpa();
		this.listaTodos();
	}

	public void alteraSenha() {
		long id = this.usuarioLogado.getUsuario().getId();
		Usuario usuario = this.usuarioDao.buscaPorId(id);
		usuario.setSenha(this.usuario.getSenha());
		this.usuarioDao.atualiza(usuario);
		
		this.usuario = new Usuario();
		this.addMessage("Senha alterada com sucesso!", null);
	}

	private void addMessage(String sumario, String detalhe) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				sumario, detalhe);
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
