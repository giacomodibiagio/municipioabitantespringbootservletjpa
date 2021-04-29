package it.prova.municipioabitantespringbootservletjpa.dao;

import java.util.List;
import java.util.Optional;

public interface IBaseDAO<T> {

	public List<T> list();

	public Optional<T> findOne(Long id);

	public void update(T input);

	public void insert(T input);

	public void delete(T input);
	
	public List<T> findByExample(T example);

	// non risulta necessario il setEntityManager...ci pensa spring
}
