package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;

@Component
public class AbitanteDAOImpl implements AbitanteDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Abitante> list() {
		return entityManager.createQuery("from Abitante", Abitante.class).getResultList();
	}

	@Override
	public Optional<Abitante> findOne(Long id) {
		Abitante result = entityManager.find(Abitante.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public Optional<Abitante> findOneEager(Long id) {
		TypedQuery<Abitante> query = entityManager.createQuery(
				"select a FROM Abitante a join fetch a.municipio where a.id = :idAbitante ", Abitante.class);
		query.setParameter("idAbitante", id);
		return query.getResultStream().findFirst();
	}

	@Override
	public void update(Abitante abitanteInstance) {
		abitanteInstance = entityManager.merge(abitanteInstance);
	}

	@Override
	public void insert(Abitante abitanteInstance) {
		entityManager.persist(abitanteInstance);
	}

	@Override
	public void delete(Abitante abitanteInstance) {
		entityManager.remove(entityManager.merge(abitanteInstance));
	}

	@Override
	public List<Abitante> findByExample(Abitante example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from Abitante a where a.id = a.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" a.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" a.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getResidenza())) {
			whereClauses.add(" a.residenza like :residenza ");
			paramaterMap.put("residenza", "%" + example.getResidenza() + "%");
		}
		if (example.getDataDiNascita() != null) {
			whereClauses.add("a.dataDiNascita >= :dataDiNascita ");
			paramaterMap.put("dataDiNascita", example.getDataDiNascita());
		}
		if (example.getMunicipio() != null && example.getMunicipio().getId() != null
				&& example.getMunicipio().getId() > 0) {
			whereClauses.add("a.municipio = :municipio ");
			paramaterMap.put("municipio", example.getMunicipio());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Abitante> typedQuery = entityManager.createQuery(queryBuilder.toString(), Abitante.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();

	}

}
