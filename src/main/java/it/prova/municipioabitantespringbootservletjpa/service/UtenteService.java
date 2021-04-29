package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

public interface UtenteService  {
	
	public List<Utente> listAll() ;

	public Utente caricaSingoloElemento(Long id) ;

	public void aggiorna(Utente utenteInstance) ;

	public void inserisciNuovo(Utente utenteInstance) ;

	public void rimuovi(Utente utenteInstance) throws Exception;
	
	public void aggiungiRuolo(Utente utenteEsistente, Ruolo ruoloInstance) ;
	
	public Utente findByUsernameAndPassword(String username, String password) ;
	
	public Utente accedi(String username, String password) ;
	
	public List<Utente> findByExample(Utente example);
	
	public Utente caricaSingoloUtenteConRuoli(Long id);

}
