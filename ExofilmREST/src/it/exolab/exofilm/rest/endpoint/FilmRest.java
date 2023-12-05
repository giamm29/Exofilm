package it.exolab.exofilm.rest.endpoint;

import static it.exolab.exofilm.rest.util.RestConstants.*;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import it.exolab.netfilm.ejb.interfaces.FilmEjbInterface;
import it.exolab.netfilm.jpa.models.Film;


@Path("/filmRest")
public class FilmRest extends BaseRest<Film, FilmEjbInterface> {

	/*
	 * se questa classe fosse stata un EJB avrei potuto usare @EJB
	 * ma RESTEASY non conosce le EJB, qundi sono costretto ad usare la lookUp,
	 * @EJB si usa solo tra ejb, se avessi usato i CDI avrei potuto usare @Injection
	 * p.s. con @Stateless o @SessionScoped avrei convertito la classe rest in un EJB nel primo caso 
	 * o avrei aggiunto un context nel secondo caso, entrambi mi avrebbero permesso di usare 
	 * l'annotation @EJB, ma sarebbe stata una procedura scorretta
	 */
	private FilmEjbInterface filmEjbInterface;
	
	public FilmRest() {
		super(FilmEjbInterface.class);
	}
	
	@POST
	@Path("/insertFilm")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response insert(Film film) {
		logInit("insert", film);
		try {
			filmEjbInterface = super.getEJB(); // new EJBFactory<FilmEjbInterface>(FilmEjbInterface.class).getEJB();
			film = filmEjbInterface.insert(film);
			
			return Response.status(RESPONSE_CODE_CREATED).entity(film).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@POST
	@Path("/insertAutoFilm")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	public Response insertAuto(Film film) {
		logInit("insertAuto", film);
		try {
			filmEjbInterface = super.getEJB();
			film = filmEjbInterface.insertAuto(film);
	
			return Response.status(RESPONSE_CODE_CREATED).entity(film).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}

	@PUT
	@Path("/updateFilm")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response update(Film film) {
		logInit("update", film);
		try {
			filmEjbInterface = super.getEJB();
			film = filmEjbInterface.update(film);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT /* RESPONSE_CODE_OK */).entity(film).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/deleteFilm")
	@Produces({ "application/json" })
	@Override
	public Response delete(Film film) {
		logInit("delete", film);
		try {
			filmEjbInterface = super.getEJB();
			filmEjbInterface.delete(film);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}

	/*
	 * utilizzando il pathParam, il parametro viene inserito direttamente nell url 
	 * es: localhost:8080/NomeProgetto/ApplicationPath/ClassPath/MethodPath/1 <-- parametro
	 */
	@GET
	@Path("/getFilm/{idFilm}")
	@Produces({ "application/json" })
	public Response findPathParam(@PathParam("idFilm") Integer idFilm) {
		logInit("findPathParam", idFilm);
		
		return find(idFilm);
	}

	/*
	 * utilizzando il queryParam, il parametro viene inserito direttamente nell'url ma con la sintassi del queryString
	 * es: localhost:8080/NomeProgetto/ApplicationPath/ClassPath/MethodPath?idFilm=1 <-- key e value del parametro
	 */
	@GET
	@Path("/getFilm")
	@Produces({ "application/json" })
	public Response findQueryParam(@QueryParam("idFilm") Integer idFilm) {
		logInit("findPathParam", idFilm);
		
		return find(idFilm);
	}
	
	@GET
	@Path("/getListaFilm")
	@Produces({ "application/json" })
	@Override
	public Response findAll() {
		logInit("findAll");
		try {
			filmEjbInterface = super.getEJB();
			List<Film> listaFilm = filmEjbInterface.findAll();
			
			return Response.status(RESPONSE_CODE_OK).entity(listaFilm).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@Override
	public Response find(Integer idFilm) {
		try {
			filmEjbInterface = super.getEJB();
			Film film = filmEjbInterface.findById(idFilm);
			
			return  Response.status(RESPONSE_CODE_OK).entity(film).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
}
