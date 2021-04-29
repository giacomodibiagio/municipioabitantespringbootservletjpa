package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.Optional;

import it.prova.municipioabitantespringbootservletjpa.model.Abitante;

public interface AbitanteDAO extends IBaseDAO<Abitante> {
	public Optional<Abitante> findOneEager(Long id);

}
