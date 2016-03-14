package br.com.square.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.square.dao.ArvoreDao;
import br.com.square.dto.ListaDeArvoreComProdutosDTO;

public class DataModelArvore extends LazyDataModel<ListaDeArvoreComProdutosDTO>{
	
	@Inject
	private ArvoreDao dao;
	
	@PostConstruct
	private void inicializa() {
		super.setRowCount(this.dao.contaTodos());
	}
	
	@Override
	public List<ListaDeArvoreComProdutosDTO> load(int inicio, int quantidade,
			String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		// TODO Auto-generated method stub
		return this.dao.listaComProdutosPaginada(inicio, quantidade);
	}
}
