package it.prova.municipioabitantespringbootservletjpa.dao;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {
	
	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice);

}
