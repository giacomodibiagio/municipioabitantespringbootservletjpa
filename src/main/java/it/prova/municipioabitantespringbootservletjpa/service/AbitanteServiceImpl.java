package it.prova.municipioabitantespringbootservletjpa.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.municipioabitantespringbootservletjpa.dao.AbitanteDAO;
import it.prova.municipioabitantespringbootservletjpa.model.Abitante;

@Service
public class AbitanteServiceImpl implements AbitanteService {

	@Autowired
	private AbitanteDAO abitanteDAO;

	@Transactional(readOnly = true)
	public List<Abitante> listAllAbitanti() {
		return abitanteDAO.list();
	}

	@Transactional(readOnly = true)
	public Abitante caricaSingoloAbitante(Long id) {
		return abitanteDAO.findOne(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Abitante caricaSingoloElementoEager(Long id) {
		return abitanteDAO.findOneEager(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Abitante abitanteInstance) {
		abitanteDAO.update(abitanteInstance);
	}

	@Transactional
	public void inserisciNuovo(Abitante abitanteInstance) {
		abitanteDAO.insert(abitanteInstance);
	}

	@Transactional
	public void rimuovi(Abitante abitanteInstance) {
		abitanteDAO.delete(abitanteInstance);
	}

	@Transactional(readOnly = true)
	public List<Abitante> findByExample(Abitante example) {
		return abitanteDAO.findByExample(example);
	}


}
