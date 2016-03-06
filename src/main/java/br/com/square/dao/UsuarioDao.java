package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.square.modelo.Usuario;

@Stateless
public class UsuarioDao {

	@Inject
	EntityManager manager;

	public void adiciona(Usuario usuario) {
		this.manager.joinTransaction();
		this.manager.persist(usuario);
	}

	public List<Usuario> lista() {
		this.manager.joinTransaction();
		return this.manager.createQuery("SELECT u FROM Usuario u",
				Usuario.class).getResultList();
	}

	public void remove(Usuario usuario) {
		this.manager.joinTransaction();
		this.manager.remove(usuario);
	}

	public void atualiza(Usuario usuario) {
		this.manager.joinTransaction();
		this.manager.merge(usuario);
	}

	public Usuario buscaPorId(long id) {
		this.manager.joinTransaction();
		return this.manager.find(Usuario.class, id);
	}

	public boolean autentica(Usuario usuario) {
		return this.buscaPorEmailESenha(usuario) != null;
	}

	public Usuario buscaPorEmailESenha(Usuario usuario) {
		String jpql = "SELECT u FROM Usuario u "
				+ "WHERE u.email = :email "
				+ "AND u.senha = :senha";
		try {
			this.manager.joinTransaction();
			TypedQuery<Usuario> query = this.manager.createQuery(jpql, Usuario.class);
			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
