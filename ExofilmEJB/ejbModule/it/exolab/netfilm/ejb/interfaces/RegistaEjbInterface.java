package it.exolab.netfilm.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import it.exolab.netfilm.jpa.models.Categoria;
import it.exolab.netfilm.jpa.models.Regista;

@Local
public interface RegistaEjbInterface extends BaseInterface<Regista> {
	
	List<Regista> findRegistaByCategoria(Categoria categoria);
	
}