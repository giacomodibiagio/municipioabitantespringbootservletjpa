package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.municipioabitantespringbootservletjpa.dao.RuoloDAO;
import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;

@Component
public class RuoloServiceImpl implements RuoloService {

	@Autowired
	private RuoloDAO ruoloDAO;

	@Transactional(readOnly = true)
	public List<Ruolo> listAll() {
		return ruoloDAO.list();
	}

	@Transactional(readOnly = true)
	public Ruolo caricaSingoloElemento(Long id) {
		return ruoloDAO.findOne(id).get();
	}

	@Transactional
	public void aggiorna(Ruolo ruoloInstance) {
		 ruoloDAO.update(ruoloInstance);
	}

	@Transactional
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloDAO.insert(ruoloInstance);
	}

	@Transactional
	public void rimuovi(Ruolo ruoloInstance) {
		ruoloDAO.delete(ruoloInstance);

	}

	@Transactional(readOnly = true)
	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) {
		return ruoloDAO.findByDescrizioneAndCodice(descrizione, codice);
	}

}