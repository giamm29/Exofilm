package it.exolab.exofilm.rest.config;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import it.exolab.exofilm.rest.endpoint.CategoriaRest;
import it.exolab.exofilm.rest.endpoint.FilmRest;
import it.exolab.exofilm.rest.endpoint.RegistaRest;


@ApplicationPath("/rest")
public class ControllerRestApplication extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<>();
		set.add(CategoriaRest.class);
		set.add(FilmRest.class);
		set.add(RegistaRest.class);
		
		return set;
	}


}
