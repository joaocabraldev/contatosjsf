package br.com.joaocabral.contatosjsf.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * Classe de persistência para a tabela cidade.
 * @author João Antônio Cabral
 */
@Entity
public class Cidade implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Boolean capital;

	@ManyToOne(targetEntity=Estado.class)
	private Estado estado;

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

	public Boolean isCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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
		Cidade other = (Cidade) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public Criteria criarCriteria(EntityManager em, Object entidade) {
		Cidade cidade = (Cidade) entidade;

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);

		if (cidade.getNome() != null && !cidade.getNome().isEmpty()) {
			criteria.add(Restrictions.ilike("nome", cidade.getNome(), MatchMode.ANYWHERE));
		}

		if (cidade.isCapital() != null) {
			criteria.add(Restrictions.eq("capital", cidade.isCapital()));
		}

		return criteria;
	}

}
