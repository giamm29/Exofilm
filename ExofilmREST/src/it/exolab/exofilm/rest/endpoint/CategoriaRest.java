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
import javax.ws.rs.core.Response;

import it.exolab.netfilm.ejb.interfaces.CategoriaEjbInterface;
import it.exolab.netfilm.jpa.models.Categoria;


@Path("/categoriaRest")
public class CategoriaRest extends BaseRest<Categoria, CategoriaEjbInterface> {
	
	CategoriaEjbInterface categoriaEjbInterface;
	
	public CategoriaRest() {
		super(CategoriaEjbInterface.class);
	}

	@POST
	@Path("/insertCategoria")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response insert(Categoria categoria) {
		logInit("insert", categoria);
		try {
			categoriaEjbInterface = super.getEJB();
			categoria = categoriaEjbInterface.insert(categoria);
			
			return Response.status(RESPONSE_CODE_CREATED).entity(categoria).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@PUT
	@Path("/updateCategoria")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response update(Categoria categoria) {
		logInit("update", categoria);
		try {
			categoriaEjbInterface = super.getEJB();
			categoria = categoriaEjbInterface.update(categoria);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT /* RESPONSE_CODE_OK */).entity(categoria).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/deleteCategoria")
	@Produces({ "application/json" })
	@Override
	public Response delete(Categoria categoria) {
		logInit("delete", categoria);
		try {
			categoriaEjbInterface = super.getEJB();
			categoriaEjbInterface.delete(categoria);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT).entity(categoria).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/getCategoria/{idCategoria}")
	@Produces({ "application/json" })
	@Override
	public Response find(@PathParam("idCategoria") Integer idCategoria) {
		logInit("find", idCategoria);
		try {
			categoriaEjbInterface = super.getEJB();
			Categoria categoria = categoriaEjbInterface.findById(idCategoria);
			
			return  Response.status(RESPONSE_CODE_OK).entity(categoria).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/getListaCategorie")
	@Produces({ "application/json" })
	@Override
	public Response findAll() {
		logInit("findAll");
		try {
			categoriaEjbInterface = super.getEJB();
			List<Categoria> listaCategorie = categoriaEjbInterface.findAll();
			
			return Response.status(RESPONSE_CODE_OK).entity(listaCategorie).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
}
