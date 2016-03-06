package br.com.square.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.square.dto.ListaDeTabulacoesDTO;
import br.com.square.modelo.Tabulacao;

@Stateless
public class TabulacaoDao {

	@Inject
	EntityManager manager;

	public void adiciona(Tabulacao tabulacao) {
		this.manager.joinTransaction();
		this.manager.persist(tabulacao);
	}

	public List<ListaDeTabulacoesDTO> lista() {
		String jpql = "SELECT NEW br.com.square.dto.ListaDeTabulacoesDTO("
					+ "t.dataChamada, "
					+ "t.terminal, "
					+ "t.produto.nome, "
					+ "t.arvore.motivo, "
					+ "t.arvore.submotivo, "
					+ "t.arvore.detalhe,"
					+ "t.descricao, "
					+ "t.usuario.nome, "
					+ "t.dataTabulacao"
				+ ") "
				+ "FROM Tabulacao t "
				+ "ORDER BY t.dataTabulacao DESC";
		
		this.manager.joinTransaction();
		TypedQuery<ListaDeTabulacoesDTO> query = this.manager.createQuery(jpql, ListaDeTabulacoesDTO.class);
		return query.getResultList();
	}

}
