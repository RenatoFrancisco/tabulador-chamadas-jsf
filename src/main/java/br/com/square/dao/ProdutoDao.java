package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.square.modelo.Produto;

@Stateless
public class ProdutoDao {

	@Inject
	private EntityManager manager;

	public List<Produto> lista() {
		this.manager.joinTransaction();
		return this.manager.createQuery("select p from Produto p",
				Produto.class).getResultList();
	}

	public Produto buscaPorId(long id) {
		this.manager.joinTransaction();
		return this.manager.find(Produto.class, id);
	}

	public void adiciona(Produto produto) {
		this.manager.joinTransaction();
		this.manager.persist(produto);
	}

	public Produto buscaPorNome(String nomeProduto) {
		String jpql = "SELECT p FROM Produto p "
				+ "WHERE p.nome = :nome";
		
		this.manager.joinTransaction();
		TypedQuery<Produto> query = this.manager.createQuery(jpql, Produto.class);
		query.setParameter("nome", nomeProduto);
		return query.getSingleResult();
	}

	public void atualiza(Produto produto) {
		this.manager.joinTransaction();
		this.manager.merge(produto);
	}
}
