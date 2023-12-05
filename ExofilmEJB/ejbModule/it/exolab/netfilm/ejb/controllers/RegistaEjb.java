package it.exolab.netfilm.ejb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import it.exolab.netfilm.ejb.interfaces.RegistaEjbInterface;
import it.exolab.netfilm.jpa.dao.RegistaDao;
import it.exolab.netfilm.jpa.models.Categoria;
import it.exolab.netfilm.jpa.models.Regista;

@Stateless(name = "RegistaEjbInterface")
@LocalBean
public class RegistaEjb implements RegistaEjbInterface, Serializable {

	private static final long serialVersionUID = -612200434240596374L;
	
	public RegistaEjb() {
		super();
	}
	
	@Override
	public Regista insert(Regista regista) {
		RegistaDao registaDao = new RegistaDao();
		return registaDao.insert(regista);
	}

	@Override
	public Regista update(Regista regista) {
		RegistaDao registaDao = new RegistaDao();
		return registaDao.update(regista);
	}

	@Override
	public void delete(Regista regista) {
		RegistaDao registaDao = new RegistaDao();
		registaDao.delete(regista);
	}

	@Override
	public Regista findById(Integer idRegista) {
		RegistaDao registaDao = new RegistaDao();
		return registaDao.findById(idRegista);
	}
	
	@Override
	public List<Regista> findAll() {
		RegistaDao registaDao = new RegistaDao();
		return registaDao.findAll();
	}

	@Override
	public List<Regista> findRegistaByCategoria(Categoria categoria) {
		RegistaDao registaDao = new RegistaDao();
		return registaDao.findRegistaByCategoria(categoria);
	}

}
