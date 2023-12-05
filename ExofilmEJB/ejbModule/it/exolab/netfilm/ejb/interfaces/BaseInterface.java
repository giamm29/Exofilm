package it.exolab.netfilm.ejb.interfaces;

import java.util.List;

public interface BaseInterface<T> {
	
	T insert(T model);
	
	T update(T model);
	
	void delete(T model);
	
	T findById(Integer idModel);
	
	List<T> findAll();
	
}
