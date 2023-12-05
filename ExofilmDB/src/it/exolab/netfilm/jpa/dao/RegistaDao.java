package it.exolab.netfilm.jpa.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import it.exolab.netfilm.jpa.models.Categoria;
import it.exolab.netfilm.jpa.models.Regista;


public class RegistaDao extends BaseDao<Regista> {

	@Override
	public Regista insert(Regista regista) {
		logInit("insert", regista);
		
		try {
			beginTransaction();
			/*
			 * questo controllo viene eseguito per verificare se l'entità è già presente nel container
			 * ed eventualmente unificarla con quella che gli stiamo passando, se le entità non corrispondono
			 * avremo un errore e quindi l'operazione non andrà a buon fine
			 */
			if (!getEntityManager().contains(regista)) {
				regista = getEntityManager().merge(regista);
			}
			getEntityManager().persist(regista);
			commitTransaction();
			
			return regista;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}
	
	/*
	 *  Questo metodo è alternativo all'insert normale.
	 *  Eventualmente si usa questa annotation per automatizare la transazione, questa si occuperà di aprire e chiudere la 
	 *  transazione e di eseguire il rollback se l'operazione va male, o la commit se l'operazione va a buon fine
	 */
	@Transactional
	public Regista insertAuto(Regista regista) {
		logInit("insertAuto", regista);
		
		try {
			if (!getEntityManager().contains(regista)) {
				regista = getEntityManager().merge(regista);
			}
			getEntityManager().persist(regista);
			
			return regista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Regista update(Regista regista) {
		logInit("update", regista);
		
		try {
			beginTransaction();
			getEntityManager().merge(regista);
			commitTransaction();
			
			return regista;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}

	@Override
	public void delete(Regista regista) {
		logInit("delete", regista);
		
		try {
			beginTransaction();
			getEntityManager().remove(getEntityManager().contains(regista)
					? regista
					: getEntityManager().merge(regista)
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
	public Regista findById(Integer id) {
		logInit("findById", id);
		
		try {
			beginTransaction();
			Regista regista = getEntityManager().find(Regista.class, id);
			commitTransaction();
			
			return regista;
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
	public List<Regista> findAll() {
		logInit("findAll");
		
		try {
			beginTransaction();
			List<Regista> listaRegisti = new ArrayList<>();
			
			// native query - SQL nativo
			Query query	= getEntityManager().createNativeQuery("SELECT * FROM regista", Regista.class);
			listaRegisti = query.getResultList();
			
			// typed query - JPQL (HQL non va con l'entityManager, ma con la sessione)
			TypedQuery<Regista> typedQuery = getEntityManager().createQuery("SELECT r FROM Regista r", Regista.class);
			listaRegisti = typedQuery.getResultList();
			
			commitTransaction();
			
			return listaRegisti;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}
	
	public List<Regista> findRegistaByCategoria(Categoria categoria) {
		logInit("findRegistaByCategoria", categoria);
		
		if(!verificaCategoria(categoria)) {
			return null;
		}
		
		try {
			beginTransaction();
			List<Regista> listaRegistiPerCategoria = new ArrayList<>();
			
			// typed query - JPQL (HQL non va con l'entityManager, ma con la sessione)
			TypedQuery<Regista> typedQuery = getEntityManager()
					.createQuery("SELECT DISTINCT r FROM Regista r JOIN r.listaFilm f JOIN f.listaCategorie c WHERE c.nome = :nomeCategoria", Regista.class);
			listaRegistiPerCategoria = typedQuery.setParameter("nomeCategoria", categoria.getNome()).getResultList();
			
			commitTransaction();
			
			return listaRegistiPerCategoria;
		} catch (Exception e) {
			e.printStackTrace();
			rollbackTransaction();
		} finally {
			getEntityManager().close();
		}
		
		return null;
	}
	
	private boolean verificaCategoria(Categoria categoria) {
		return null != categoria && categoria.getNome() != null;
	}

}
