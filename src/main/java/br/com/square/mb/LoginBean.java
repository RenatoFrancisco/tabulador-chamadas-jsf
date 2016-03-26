package br.com.square.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.square.dao.UsuarioDao;
import br.com.square.modelo.Usuario;

@Named
@RequestScoped
public class LoginBean implements Serializable{
	private final static String TELA_LOGIN = "login?faces-redirect=true";

	private final static String TELA_PRINCIPAL = "tabulador?faces-redirect=true";
	
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Inject
	private UsuarioLogadoBean usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		if(usuarioDao.autentica(this.usuario)){
			this.usuario = this.usuarioDao.buscaPorEmailESenha(this.usuario);
			this.usuarioLogado.loga(this.usuario);
			return TELA_PRINCIPAL;
		}
		FacesContext.getCurrentInstance()
			.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail e/ou senha inválidos.", null));
		return null;
	}
	
	public String efetuaLogout() {
		this.usuarioLogado.desloga();
		return TELA_LOGIN;
	}
}
