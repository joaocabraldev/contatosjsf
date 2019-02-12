package br.com.joaocabral.contatosjsf.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import br.com.joaocabral.contatosjsf.model.Entidade;
import br.com.joaocabral.contatosjsf.util.FiltroTabela;
import br.com.joaocabral.contatosjsf.util.NegocioException;

public class RepositorioImpl<T extends Entidade> implements Repositorio<T> {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	private final Class<T> classe;

	public RepositorioImpl(EntityManager em, Class<T> classe) {
		this.em = em;
		this.classe = classe;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listaTodos() {
		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(classe);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listaTodos(FiltroTabela filtro, T entidade) {
		Criteria criteria = entidade.criarCriteria(em, entidade);

		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());

		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T obterPorFiltro(T entidade) {
		Criteria criteria = entidade.criarCriteria(em, entidade);
		return (T) criteria.uniqueResult();
	}

	@Override
	public int contar(FiltroTabela filtro, T entidade) {
		Criteria criteria = entidade.criarCriteria(em, entidade);
		criteria.setProjection(Projections.rowCount());
		return ((Number) criteria.uniqueResult()).intValue();
	}

	@Override
	public void salvar(T entidade) throws NegocioException {
		try {
			if (entidade.getId() == 0) {
				em.persist(entidade);
				em.flush();
			} else {
				em.merge(entidade);
				em.flush();
			}
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@Override
	public void deletar(T entidade) throws NegocioException {
		try {
			T entidadeDeletar = em.find(classe, entidade.getId());
			em.remove(entidadeDeletar);
		} catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
	}

}
