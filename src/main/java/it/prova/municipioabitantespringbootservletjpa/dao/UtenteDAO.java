package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.List;
import java.util.Optional;

import it.prova.municipioabitantespringbootservletjpa.model.Ruolo;
import it.prova.municipioabitantespringbootservletjpa.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	
	public List<Utente> findAllByRuolo(Ruolo ruoloInput);
	public Optional<Utente> findByUsernameAndPassword(String username,String password);
	public Optional<Utente> login(String username,String password);
	Utente findEager(long idUtente);

}
