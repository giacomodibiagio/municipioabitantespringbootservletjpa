package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.List;
import java.util.Optional;

import it.prova.municipioabitantespringbootservletjpa.model.Municipio;

public interface MunicipioDAO extends IBaseDAO<Municipio> {

	public List<Municipio> findAllOrderByDescrizione();
	public List<Municipio> findAllByDescrizioneILike(String term);
	Municipio findEager(long idMunicipio);
	Optional<Municipio> findOneEager(Long id);
}
