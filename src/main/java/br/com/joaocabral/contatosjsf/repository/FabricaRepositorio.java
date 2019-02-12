package br.com.joaocabral.contatosjsf.repository;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.joaocabral.contatosjsf.model.Entidade;

public class FabricaRepositorio {

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Produces
	@Dependent
	public <T extends Entidade> Repositorio<T> create(InjectionPoint point) {
		ParameterizedType type = (ParameterizedType) point.getType();
		@SuppressWarnings("rawtypes")
		Class classe = (Class) type.getActualTypeArguments()[0];
		return new RepositorioImpl<T>(em, classe);
	}

}
