package it.exolab.netfilm.ejb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import it.exolab.netfilm.ejb.interfaces.FilmEjbInterface;
import it.exolab.netfilm.jpa.dao.FilmDao;
import it.exolab.netfilm.jpa.models.Film;

@Stateless(name = "FilmEjbInterface")
@LocalBean
public class FilmEjb implements FilmEjbInterface, Serializable {

	private static final long serialVersionUID = 7648982483597477548L;
	
	public FilmEjb() {
		super();
	}

	@Override
	public Film insert(Film film) {
		FilmDao filmDao = new FilmDao();
		return filmDao.insert(film);
	}
	
	public Film insertAuto(Film film) {
		FilmDao filmDao = new FilmDao();
		return filmDao.insertAuto(film);
	}

	@Override
	public Film update(Film film) {
		FilmDao filmDao = new FilmDao();
		return filmDao.update(film);
	}

	@Override
	public void delete(Film film) {
		FilmDao filmDao = new FilmDao();
		filmDao.delete(film);
	}

	@Override
	public Film findById(Integer idFilm) {
		FilmDao filmDao = new FilmDao();
		return filmDao.findById(idFilm);
	}
	
	@Override
	public List<Film> findAll() {
		FilmDao filmDao = new FilmDao();
		return filmDao.findAll();
	}
	
	@Override
	public List<Film> findByTitolo(String titolo) {
		FilmDao filmDao = new FilmDao();
		return filmDao.findByTitolo(titolo);
	}

}
