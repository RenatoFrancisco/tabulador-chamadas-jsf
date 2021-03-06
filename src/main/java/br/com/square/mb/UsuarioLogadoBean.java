package br.com.square.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.square.modelo.Usuario;

@Named
@SessionScoped
public class UsuarioLogadoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public void loga(Usuario usuario) {
		this.usuario = usuario;		
	}

	public void desloga() {
		this.usuario = null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean isLogado() {
		return this.usuario != null;
	}

}
