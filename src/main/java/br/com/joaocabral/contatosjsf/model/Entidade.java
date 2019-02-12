package br.com.joaocabral.contatosjsf.model;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;

/**
 * Modelo de Entidade para o Sistema.
 * @author João Antônio Cabral
 */
public interface Entidade extends Serializable {

	Long getId();

	Criteria criarCriteria(EntityManager em, Object entidade);

}
