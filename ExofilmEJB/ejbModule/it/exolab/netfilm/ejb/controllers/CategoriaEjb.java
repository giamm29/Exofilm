package it.exolab.netfilm.ejb.controllers;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import it.exolab.netfilm.ejb.interfaces.CategoriaEjbInterface;
import it.exolab.netfilm.jpa.dao.CategoriaDao;
import it.exolab.netfilm.jpa.models.Categoria;

@Stateless(name = "CategoriaEjbInterface")
@LocalBean
public class CategoriaEjb implements CategoriaEjbInterface, Serializable {

	private static final long serialVersionUID = -3454060561209780303L;

	public CategoriaEjb() {
		super();
	}
	
	@Override
	public Categoria insert(Categoria categoria) {
		CategoriaDao categoriaDao = new CategoriaDao();
		return categoriaDao.insert(categoria);
	}

	@Override
	public Categoria update(Categoria categoria) {
		CategoriaDao categoriaDao = new CategoriaDao();
		return categoriaDao.update(categoria);
	}

	@Override
	public void delete(Categoria categoria) {
		CategoriaDao categoriaDao = new CategoriaDao();
		categoriaDao.delete(categoria);
	}

	@Override
	public Categoria findById(Integer idCategoria) {
		CategoriaDao categoriaDao = new CategoriaDao();
		return categoriaDao.findById(idCategoria);
	}

	@Override
	public List<Categoria> findAll() {
		CategoriaDao categoriaDao = new CategoriaDao();
		return categoriaDao.findAll();
	}

}
