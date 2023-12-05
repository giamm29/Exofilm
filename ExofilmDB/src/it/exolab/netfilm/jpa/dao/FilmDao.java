package it.exolab.netfilm.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.exolab.netfilm.jpa.models.Film;


public class FilmDao extends BaseDao<Film> {

	@Override
	public Film insert(Film film) {
		logInit("insert", film);
		
		try {
			beginTransaction();
			/*
			 * questo controllo viene eseguito per verificare se l'entità è già presente nel container
			 * ed eventualmente unificarla con quella che gli stiamo passando, se le entità non corrispondono
			 * avremo un errore e quindi l'operazione non andrà a buon fine
			 */
			if (!getEntityManager().contains(film)) {
				film = getEntityManager().merge(film);
			}
			getEntityManager().persist(film);
			commitTransaction();
			
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}
	
	@Transactional
	public Film insertAuto(Film film) {
		logInit("insertauto", film);
		
		try {
			if (!getEntityManager().contains(film)) {
				film = getEntityManager().merge(film);
			}
			getEntityManager().persist(film);
			
			return film;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@Override
	public Film update(Film film) {
		logInit("update", film);
		
		try {
			beginTransaction();
			getEntityManager().merge(film);
			commitTransaction();
			
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@Override
	public void delete(Film film) {
		logInit("delete", film);
		
		try {
			beginTransaction();
			getEntityManager().remove(getEntityManager().contains(film)
					? film
					: getEntityManager().merge(film)
			);
			commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().flush();
			getEntityManager().close();
		}
	}
	
	@Override
	public Film findById(Integer id) {
		logInit("findById", id);
		
		try {
			beginTransaction();
			Film film = getEntityManager().find(Film.class, id);
			commitTransaction();
			
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> findAll() {
		logInit("findAll");
		
		try {
			beginTransaction();
			List<Film> listaFilm = new ArrayList<>();
			
			// native query - SQL nativo
			Query query = getEntityManager().createNativeQuery("SELECT * FROM film", Film.class);
			listaFilm = query.getResultList();
			
			// typed query - JPQL (HQL non va con l'entityManager, ma con la sessione)
			TypedQuery<Film> typedQuery = getEntityManager().createQuery("SELECT f FROM Film f", Film.class);
			listaFilm = typedQuery.getResultList();
			
			commitTransaction();
			
			return listaFilm;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Film> findByTitolo(String titolo) {
		System.out.println("dentro FilmDao -- findByTitolo -- titolo: " + titolo);
		
		try {
			beginTransaction();
			List<Film> listaFilm = new ArrayList<>();
			
			// native query - SQL nativo - esempio di parametro posizionale
			Query nativeQuery = getEntityManager().createNativeQuery("SELECT * FROM film WHERE titolo LIKE ? ", Film.class);
			nativeQuery.setParameter(1, "%" + titolo + "%");
			listaFilm = nativeQuery.getResultList();
			
			// typed query - JPQL - esempio di parametro nominale
			TypedQuery<Film> typedQuery = getEntityManager().createQuery("SELECT f FROM Film f WHERE f.titolo LIKE :titolo", Film.class);
			typedQuery.setParameter("titolo", "%" + titolo + "%");
			listaFilm = typedQuery.getResultList();
			
			commitTransaction();
			
			return listaFilm;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

}
