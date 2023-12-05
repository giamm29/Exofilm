package it.exolab.exofilm.rest.endpoint;

import javax.ws.rs.core.Response;

import it.exolab.exofilm.rest.config.EJBFactory;


public abstract class BaseRest<T, S> {
	
	private Class<S> interfaceClass;
	
	public BaseRest(Class<S> interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	
	public abstract Response insert(T model);
	
	public abstract Response update(T model);
	
	public abstract Response delete(T model);
	
	public abstract Response find(Integer id);
	
	public abstract Response findAll();
	
	protected S getEJB() throws Exception {
		return new EJBFactory<S>(interfaceClass).getEJB();
	}
	
	protected void logInit(String method) {
		logInit(method, null);
	}
	
	protected void logInit(String method, Object object) {
		System.out.println(" -- Dentro " + this.getClass().getSimpleName() + " -- " + method + " -- "
			+ (object != null
				? object.toString()
				: ""));
	}
	
}
