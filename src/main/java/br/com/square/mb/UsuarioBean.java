package br.com.square.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.PerfilDao;
import br.com.square.dao.UsuarioDao;
import br.com.square.modelo.Perfil;
import br.com.square.modelo.Usuario;

@Named
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String perfilNome;
	private List<Usuario> usuarios;
	private List<Perfil> perfis;

	@Inject
	private UsuarioDao usuarioDao;

	@Inject
	private PerfilDao perfilDao;

	public void salva() {
		Perfil perfil = this.perfilDao.buscaPorNome(this.perfilNome);

		if (this.usuario.getId() == 0L) {
			this.usuario.setPerfil(perfil);
			this.usuarioDao.adiciona(this.usuario);
		} else {
			this.usuario.setPerfil(perfil);
			this.usuarioDao.atualiza(usuario);
		}

		this.limpa();
		this.listaTodos();
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

	private void limpa() {
		this.usuario = new Usuario();
		this.perfilNome = null;
	}

	private void listaTodos() {
		this.usuarios = usuarioDao.lista();
	}
}
