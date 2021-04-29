package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;

@Component
public class MunicipioDAOImpl implements MunicipioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Municipio> list() {
		return entityManager.createQuery("from Municipio",Municipio.class).getResultList();
	}
	
	@Override
	public List<Municipio> findAllOrderByDescrizione() {
		return entityManager.createQuery("from Municipio m order by m.descrizione",Municipio.class).getResultList();
	}

	@Override
	public Optional<Municipio> findOne(Long id) {
		Municipio result = entityManager.find(Municipio.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Municipio municipioInstance) {
		municipioInstance = entityManager.merge(municipioInstance);
	}

	@Override
	public void insert(Municipio municipioInstance) {
		entityManager.persist(municipioInstance);
	}

	@Override
	public void delete(Municipio municipioInstance) {
		entityManager.remove(entityManager.merge(municipioInstance));
	}

	@Override
	public List<Municipio> findByExample(Municipio example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select m from Municipio m where m.id = m.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" m.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" m.codice like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getUbicazione())) {
			whereClauses.add(" m.ubicazione like :ubicazione ");
			paramaterMap.put("ubicazione", "%" + example.getUbicazione() + "%");
		}
		
		
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Municipio> typedQuery = entityManager.createQuery(queryBuilder.toString(), Municipio.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Municipio> findAllByDescrizioneILike(String term) {
		term = term != null ? term.toLowerCase() : "";
		TypedQuery<Municipio> query = entityManager
				.createQuery("select u FROM Municipio u where  lower(u.descrizione) like :termInput ", Municipio.class);
		query.setParameter("termInput", '%' + term + '%');

		return query.getResultList();
	}
	@Override
	public Municipio findEager(long idMunicipio) {
		Query q = entityManager.createQuery("SELECT m FROM Municipio m LEFT JOIN FETCH m.abitanti a WHERE m.id = :id");
		q.setParameter("id", idMunicipio);
		return (Municipio) q.getSingleResult();
	}
	@Override
	public Optional<Municipio> findOneEager(Long id) {
		return entityManager.createQuery("from Municipio m left join fetch m.abitanti where m.id=:idMunicipio", Municipio.class)
				.setParameter("idMunicipio", id).getResultList().stream().findFirst();
	}

}
