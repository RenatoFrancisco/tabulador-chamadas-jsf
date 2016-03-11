package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.square.modelo.Site;

@Stateless
public class SiteDao {

	@Inject
	private EntityManager manager;

	public void adiciona(Site site) {
		this.manager.joinTransaction();
		this.manager.persist(site);
	}

	public void atualiza(Site site) {
		this.manager.joinTransaction();
		this.manager.merge(site);
	}

	public List<Site> lista() {
		this.manager.joinTransaction();
		return this.manager.createQuery("SELECT s FROM Site s", Site.class)
				.getResultList();
	}

	public List<Site> listaAtivos() {
		this.manager.joinTransaction();
		return this.manager.createQuery(
				"SELECT s FROM Site s WHERE s.ativo = true", Site.class)
				.getResultList();
	}

	public Site buscaPorId(long id) {
		this.manager.joinTransaction();
		return this.manager.find(Site.class, id);
	}

	public Site buscaPorSigla(String sigla) {
		String jpql = "SELECT s FROM Site s WHERE s.sigla = :sigla";
		this.manager.joinTransaction();
		TypedQuery<Site> query = this.manager.createQuery(jpql, Site.class);
		query.setParameter("sigla", sigla);
		return query.getSingleResult();
	}
}
