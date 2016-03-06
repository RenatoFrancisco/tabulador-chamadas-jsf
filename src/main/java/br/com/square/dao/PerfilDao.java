package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.square.modelo.Perfil;

@Stateless
public class PerfilDao {

	@Inject
	EntityManager manager;

	public List<Perfil> lista() {
		this.manager.joinTransaction();
		return this.manager.createQuery("SELECT p FROM Perfil p", Perfil.class)
				.getResultList();
	}

	public Perfil buscaPorId(long id) {
		this.manager.joinTransaction();
		return this.manager.find(Perfil.class, id);
	}

	public Perfil buscaPorNome(String perfilNome) {
		String jpql = "SELECT p FROM Perfil p WHERE p.nome = :perfilNome";
		
		this.manager.joinTransaction();
		TypedQuery<Perfil> query = this.manager.createQuery(jpql, Perfil.class);
		query.setParameter("perfilNome", perfilNome);
		return query.getSingleResult();
	}
}
