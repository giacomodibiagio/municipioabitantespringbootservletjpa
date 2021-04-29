package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.municipioabitantespringbootservletjpa.dao.MunicipioDAO;
import it.prova.municipioabitantespringbootservletjpa.model.Municipio;

@Component
public class MunicipioServiceImpl implements MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;

	@Transactional(readOnly = true)
	public List<Municipio> listAllMunicipi() {
		return municipioDAO.list();
	}

	@Transactional(readOnly = true)
	public List<Municipio> listAllMunicipiOrderByDescrizione() {
		return municipioDAO.findAllOrderByDescrizione();
	}

	@Transactional(readOnly = true)
	public Municipio caricaSingoloMunicipio(Long id) {
		return municipioDAO.findOne(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Municipio municipioInstance) {
		municipioDAO.update(municipioInstance);
	}

	@Transactional
	public void inserisciNuovo(Municipio municipioInstance) {
		municipioDAO.insert(municipioInstance);
	}

	@Transactional
	public void rimuovi(Municipio municipioInstance) throws Exception {
		Municipio municipio = municipioDAO.findEager(municipioInstance.getId());
		if (municipio.getAbitanti().isEmpty()) {
			municipioDAO.delete(municipioInstance);
		}

		else {
			throw new Exception("Municipio non eliminabile. Ci sono ancora abitanti assegnati.");
		}
	}

	@Transactional(readOnly = true)
	public List<Municipio> findByExample(Municipio example) {
		return municipioDAO.findByExample(example);
	}

	@Transactional(readOnly = true)
	public List<Municipio> cercaByDescrizioneILike(String term) {
		return municipioDAO.findAllByDescrizioneILike(term);
	}
	
	@Transactional
	public Municipio caricaSingoloMunicipioConAbitanti(Long id) {
		return municipioDAO.findOneEager(id).get();	
	}

}
