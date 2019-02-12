package br.com.joaocabral.contatosjsf.repository;

import java.io.Serializable;
import java.util.List;

import br.com.joaocabral.contatosjsf.model.Entidade;
import br.com.joaocabral.contatosjsf.util.FiltroTabela;
import br.com.joaocabral.contatosjsf.util.NegocioException;

public interface Repositorio<T extends Entidade> extends Serializable {

	List<T> listaTodos();

	List<T> listaTodos(FiltroTabela filtro, T entidade);

	T obterPorFiltro(T entidade);

	int contar(FiltroTabela filtro, T entidade);

	void salvar(T entidade) throws NegocioException;

	void deletar(T entidade) throws NegocioException;


}
