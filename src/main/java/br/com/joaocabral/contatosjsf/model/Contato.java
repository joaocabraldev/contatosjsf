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
 * Classe de persistência para a tabela contato.
 * @author João Antônio Cabral
 */
@Entity
public class Contato implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;

	@ManyToOne(targetEntity = Estado.class)
	private Estado estado;

	@ManyToOne(targetEntity = Cidade.class)
	private Cidade cidade;

	private String setor;

	private String rua;

	private String complemento;

	private String telefone;

	private String email;

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		Contato other = (Contato) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public Criteria criarCriteria(EntityManager em, Object entidade) {
		Contato contato = (Contato) entidade;

		Session session = em.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cidade.class);

		if (contato.getNome() != null && !contato.getNome().isEmpty()) {
			criteria.add(Restrictions.ilike("nome", contato.getNome(), MatchMode.ANYWHERE));
		}

		if (contato.getTelefone() != null && !contato.getTelefone().isEmpty()) {
			criteria.add(Restrictions.ilike("telefone", contato.getTelefone(), MatchMode.ANYWHERE));
		}

		return criteria;

	}

}
