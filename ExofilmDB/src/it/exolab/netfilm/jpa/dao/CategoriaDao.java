package it.exolab.netfilm.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.exolab.netfilm.jpa.models.Categoria;


public class CategoriaDao extends BaseDao<Categoria> {

	@Override
	public Categoria insert(Categoria categoria) {
		logInit("insert", categoria);
		
		try {
			beginTransaction();
			/*
			 * questo controllo viene eseguito per verificare se l'entità è già presente nel container
			 * ed eventualmente unificarla con quella che gli stiamo passando, se le entità non corrispondono
			 * avremo un errore e quindi l'operazione non andrà a buon fine
			 */
			if (!getEntityManager().contains(categoria)) {
				categoria = getEntityManager().merge(categoria);
			}
			getEntityManager().persist(categoria);
			commitTransaction();
			
			return categoria;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@Override
	public Categoria update(Categoria categoria) {
		logInit("update", categoria);
		
		try {
			beginTransaction();
			getEntityManager().merge(categoria);
			commitTransaction();
			
			return categoria;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@Override
	public void delete(Categoria categoria) {
		logInit("delete", categoria);
		
		try {
			beginTransaction();
			getEntityManager().remove(getEntityManager().contains(categoria)
					? categoria
					: getEntityManager().merge(categoria)
			);
			commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().flush();
			getEntityManager().clear();
			getEntityManager().close();
		}
	}

	@Override
	public Categoria findById(Integer id) {
		logInit("findById", id);
		
		try {
			beginTransaction();
			Categoria categoria = getEntityManager().find(Categoria.class, id);
			commitTransaction();
			
			return categoria;
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
	public List<Categoria> findAll() {
		logInit("findAll");
		
		try {
			beginTransaction();
			List<Categoria> listaCategorie = new ArrayList<>();
			
			// native query - SQL nativo
			Query query = getEntityManager().createNativeQuery("SELECT * FROM categoria", Categoria.class);
			listaCategorie = query.getResultList();
			
			// typed query - JPQL (HQL non va con l'entityManager, ma con la sessione)
			TypedQuery<Categoria> typedQuery = getEntityManager().createQuery("SELECT c FROM Categoria c", Categoria.class);
			listaCategorie = typedQuery.getResultList();
			
			commitTransaction();
			
			return listaCategorie;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

}
