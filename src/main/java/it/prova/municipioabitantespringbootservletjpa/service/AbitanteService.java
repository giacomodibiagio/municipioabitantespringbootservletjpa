package it.prova.municipioabitantespringbootservletjpa.service;


import java.util.List;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;

public interface AbitanteService {

	public List<Abitante> listAllAbitanti();

	public Abitante caricaSingoloAbitante(Long id);
	
	public Abitante caricaSingoloElementoEager(Long id);

	public void aggiorna(Abitante abitanteInstance);

	public void inserisciNuovo(Abitante abitanteInstance);

	public void rimuovi(Abitante abitanteInstance);

	public List<Abitante> findByExample(Abitante example);

}
