package br.com.joaocabral.contatosjsf.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.joaocabral.contatosjsf.model.Estado;
import br.com.joaocabral.contatosjsf.repository.Repositorio;
import br.com.joaocabral.contatosjsf.util.FiltroTabela;
import br.com.joaocabral.contatosjsf.util.GeradorMensagem;

@Named
@ViewScoped
public class EstadoController implements Serializable {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private Repositorio<Estado> repositorio;

	private FiltroTabela filtro = new FiltroTabela();

	private Estado itemPesquisa = new Estado();
	private Estado itemSelecionado = new Estado();

	private LazyDataModel<Estado> model;

	public void consultar() {

		model = new LazyDataModel<Estado>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Estado> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(repositorio.contar(filtro, itemPesquisa));

				return repositorio.listaTodos(filtro, itemPesquisa);

			}

		};

	}

	public void inicializar() {
		consultar();
	}

	public void selecionarItem(Estado estado) {
		itemSelecionado = estado;
	}

	public void novo() {
		itemSelecionado = new Estado();
	}

	public void salvar() {
		try {
			repositorio.salvar(itemSelecionado);
			itemSelecionado = new Estado();
			GeradorMensagem.mostrarMensagemInformacao("Sucesso!", "Salvo com sucesso!");
			consultar();
		} catch (Exception e) {
			GeradorMensagem.mostrarMensagemErro("Erro ao salvar estado", e.getMessage());
		}
	}

	public void deletar() {
		try {
			repositorio.deletar(itemSelecionado);
			itemSelecionado = new Estado();
			GeradorMensagem.mostrarMensagemInformacao("Sucesso!", "Deletado com sucesso!");
			consultar();
		} catch (Exception e) {
			GeradorMensagem.mostrarMensagemErro("Erro ao deletar estado", e.getMessage());
		}

	}

	public FiltroTabela getFiltro() {
		return filtro;
	}

	public Estado getItemPesquisa() {
		return itemPesquisa;
	}

	public Estado getItemSelecionado() {
		return itemSelecionado;
	}

	public LazyDataModel<Estado> getModel() {
		return model;
	}

}
