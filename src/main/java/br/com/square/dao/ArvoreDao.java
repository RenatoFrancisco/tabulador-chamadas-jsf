package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.square.dto.ListaDeArvoreComProdutosDTO;
import br.com.square.modelo.Arvore;
import br.com.square.modelo.Produto;

@Stateless
public class ArvoreDao {

	@Inject
	private EntityManager manager;

	public List<String> listaMotivos(long produtoSelecionado) {
		String jpql = "SELECT DISTINCT a.motivo FROM Arvore a "
				+ "JOIN a.produtos p WHERE p.id = :produto "
				+ "AND a.ativo = true "
				+ "ORDER BY a.motivo";

		this.manager.joinTransaction();

		TypedQuery<String> query = this.manager.createQuery(jpql, String.class);
		query.setParameter("produto", produtoSelecionado);

		return query.getResultList();
	}

	public List<String> listaSubMotivos(long produtoSelecionado,
			String motivoSelecionado) {
		
		String jpql = "SELECT DISTINCT a.submotivo FROM Arvore a "
				+ "JOIN a.produtos p WHERE p.id = :produto AND a.motivo = :motivo "
				+ "AND a.ativo = true "
				+ "ORDER BY a.submotivo";

		this.manager.joinTransaction();

		TypedQuery<String> query = this.manager.createQuery(jpql, String.class);
		query.setParameter("produto", produtoSelecionado);
		query.setParameter("motivo", motivoSelecionado);

		return query.getResultList();
	}

	public List<String> listaDetalhes(long produtoSelecionado,
			String motivoSelecionado, String subMotivoSelecionado) {

		String jpql = "SELECT DISTINCT a.detalhe FROM Arvore a "
				+ "JOIN a.produtos p WHERE p.id = :produto AND a.motivo = :motivo "
				+ "AND a.submotivo = :submotivo " 
				+ "AND a.ativo = true "
				+ "ORDER BY a.detalhe";

		this.manager.joinTransaction();

		TypedQuery<String> query = this.manager.createQuery(jpql, String.class);
		query.setParameter("produto", produtoSelecionado);
		query.setParameter("motivo", motivoSelecionado);
		query.setParameter("submotivo", subMotivoSelecionado);

		return query.getResultList();
	}

	public Arvore buscaPorAvoreCompleta(Produto produto, String motivo,
			String submotivo, String detalhe) {

		String jpql = "SELECT a FROM Arvore a " + "JOIN a.produtos p "
				+ "WHERE p.nome = :produto AND a.motivo = :motivo "
				+ "AND a.submotivo = :submotivo " + "AND a.detalhe = :detalhe";

		this.manager.joinTransaction();
		TypedQuery<Arvore> query = this.manager.createQuery(jpql, Arvore.class);
		query.setParameter("produto", produto.getNome());
		query.setParameter("motivo", motivo);
		query.setParameter("submotivo", submotivo);
		query.setParameter("detalhe", detalhe);

		return query.getSingleResult();
	}

	public void adiciona(Arvore arvore) {
		this.manager.joinTransaction();
		this.manager.persist(arvore);
	}

	public List<Arvore> lista() {
		this.manager.joinTransaction();
		return this.manager.createQuery("SELECT a FROM Arvore a", Arvore.class).getResultList();
	}
	
	public List<ListaDeArvoreComProdutosDTO> listaComProdutos() {
		String jpql = "SELECT NEW br.com.square.dto.ListaDeArvoreComProdutosDTO("
				+ "p.nome, "
				+ "a.motivo, "
				+ "a.submotivo, "
				+ "a.detalhe, "
				+ "a.ativo,"
				+ "a.id "
				+ ") "
				+ "FROM Arvore a "
				+ "JOIN a.produtos p "
				+ "ORDER BY p.nome, a.motivo";
		
		this.manager.joinTransaction();
		TypedQuery<ListaDeArvoreComProdutosDTO> query = this.manager.createQuery(jpql, ListaDeArvoreComProdutosDTO.class);
		return query.getResultList();
	}

	public void atualiza(Arvore arvore) {
		this.manager.joinTransaction();
		this.manager.merge(arvore);
	}

	public int contaTodos() {
		this.manager.joinTransaction();
		
		long result = (Long) this.manager.createQuery("SELECT COUNT(a) FROM Arvore a")
								.getSingleResult();
		return (int) result;
	}

	public List<ListaDeArvoreComProdutosDTO> listaComProdutosPaginada(
			int inicio, int quantidade) {
		
		String jpql = "SELECT NEW br.com.square.dto.ListaDeArvoreComProdutosDTO("
				+ "p.nome, "
				+ "a.motivo, "
				+ "a.submotivo, "
				+ "a.detalhe, "
				+ "a.ativo,"
				+ "a.id "
				+ ") "
				+ "FROM Arvore a "
				+ "JOIN a.produtos p "
				+ "ORDER BY p.nome, a.motivo";
		
		this.manager.joinTransaction();
		List<ListaDeArvoreComProdutosDTO> lista = this.manager.createQuery(jpql, ListaDeArvoreComProdutosDTO.class)
			.setFirstResult(inicio)
			.setMaxResults(quantidade)
			.getResultList();
		return lista;
	}
}
