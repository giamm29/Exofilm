package it.exolab.netfilm.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import it.exolab.netfilm.jpa.models.Film;


@Local
public interface FilmEjbInterface extends BaseInterface<Film> {
	
	Film insertAuto(Film film);
	
	List<Film> findByTitolo(String titolo);
	
}
