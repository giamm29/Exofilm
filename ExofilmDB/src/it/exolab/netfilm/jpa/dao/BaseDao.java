package it.exolab.netfilm.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;

import it.exolab.netfilm.jpa.connection.EntityManagerProvider;


public abstract class BaseDao<T> {
	
	public abstract T insert(T model);
	
	public abstract T update(T model);
	
	public abstract void delete(T model);
	
	public abstract T findById(Integer id);
	
	public abstract List<T> findAll();
	
	protected EntityManager getEntityManager() {
		return EntityManagerProvider.getEntityManager();
	}
	
	protected void beginTransaction() throws Exception {
		EntityManagerProvider.beginTransaction();
	}
	
	protected void commitTransaction() {
		EntityManagerProvider.commitTransaction();
	}
	
	protected void rollbackTransaction() {
		EntityManagerProvider.rollbackTransaction();
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
