package it.prova.municipioabitantespringbootservletjpa.service;

import java.util.List;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;

public interface MunicipioService {
	
	public List<Municipio> listAllMunicipi() ;
	
	public List<Municipio> listAllMunicipiOrderByDescrizione() ;

	public Municipio caricaSingoloMunicipio(Long id);

	public void aggiorna(Municipio municipioInstance);

	public void inserisciNuovo(Municipio municipioInstance);

	public void rimuovi(Municipio municipioInstance) throws Exception;

	public List<Municipio> findByExample(Municipio example);
	
	public List<Municipio> cercaByDescrizioneILike(String term);
	
	public Municipio caricaSingoloMunicipioConAbitanti(Long id);

}
