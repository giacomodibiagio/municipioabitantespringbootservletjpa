package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;

public interface RuoloService {
	public List<Ruolo> listAll() ;

	public Ruolo caricaSingoloElemento(Long id) ;

	public void aggiorna(Ruolo ruoloInstance) ;

	public void inserisciNuovo(Ruolo ruoloInstance) ;

	public void rimuovi(Ruolo ruoloInstance) ;

	public Ruolo cercaPerDescrizioneECodice(String descrizione, String codice) ;

}
