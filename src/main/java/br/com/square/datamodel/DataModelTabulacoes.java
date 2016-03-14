package br.com.square.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.square.dao.TabulacaoDao;
import br.com.square.dto.ListaDeTabulacoesDTO;

public class DataModelTabulacoes extends LazyDataModel<ListaDeTabulacoesDTO>{
	
	@Inject
	private TabulacaoDao dao;
	
	@PostConstruct
	private void inicializa() {
		super.setRowCount(this.dao.ContaTodos());
	}
	
	@Override
	public List<ListaDeTabulacoesDTO> load(int inicio, int quantidade,
			String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		return this.dao.listaTodosPaginada(inicio, quantidade);
	}
	
}
