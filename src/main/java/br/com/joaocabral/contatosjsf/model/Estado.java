package br.com.joaocabral.contatosjsf.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Classe de persistência para a tabela estado.
 * @author João Antônio Cabral
 */
@Entity
public class Estado implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nome + " - " + sigla;
	}

	@Override
	public Criteria criarCriteria(EntityManager em, Object entidade) {
		Estado estado = (Estado) entidade;

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Estado.class);

		if (estado.getNome() != null && !estado.getNome().isEmpty()) {
			criteria.add(Restrictions.ilike("nome", estado.getNome(), MatchMode.ANYWHERE));
		}

		if (estado.getSigla() != null && !estado.getSigla().isEmpty()) {
			criteria.add(Restrictions.ilike("sigla", estado.getSigla(), MatchMode.ANYWHERE));
		}

		return criteria;
	}

}
