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

import it.exolab.netfilm.ejb.interfaces.RegistaEjbInterface;
import it.exolab.netfilm.jpa.models.Regista;


@Path("/registaRest")
public class RegistaRest extends BaseRest<Regista, RegistaEjbInterface> {
	
	RegistaEjbInterface registaEjbInterface;
	
	public RegistaRest() {
		super(RegistaEjbInterface.class);
	}

	@POST
	@Path("/insertRegista")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response insert(Regista regista) {
		logInit("insert", regista);
		try {
			registaEjbInterface = super.getEJB();
			regista = registaEjbInterface.insert(regista);
			
			return Response.status(RESPONSE_CODE_CREATED).entity(regista).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@PUT
	@Path("/updateRegista")
	@Produces({ "application/json" })
	@Consumes({ "application/json" })
	@Override
	public Response update(Regista regista) {
		logInit("update", regista);
		try {
			registaEjbInterface = super.getEJB();
			regista = registaEjbInterface.update(regista);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT /* RESPONSE_CODE_OK */).entity(regista).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
	@DELETE
	@Path("/deleteRegista")
	@Produces({ "application/json" })
	@Override
	public Response delete(Regista regista) {
		logInit("delete", regista);
		try {
			registaEjbInterface = super.getEJB();
			registaEjbInterface.delete(regista);
			
			return Response.status(RESPONSE_CODE_NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/getRegista/{idRegista}")
	@Produces({ "application/json" })
	@Override
	public Response find(@PathParam("idRegista") Integer idRegista) {
		logInit("find", idRegista);
		try {
			registaEjbInterface = super.getEJB();
			Regista regista = registaEjbInterface.findById(idRegista);
			
			return  Response.status(RESPONSE_CODE_OK).entity(regista).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/getListaRegisti")
	@Produces({ "application/json" })
	@Override
	public Response findAll() {
		logInit("findAll");
		try {
			registaEjbInterface = super.getEJB();
			List<Regista> listaRegisti = registaEjbInterface.findAll();
			
			return  Response.status(RESPONSE_CODE_OK).entity(listaRegisti).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return Response.status(RESPONSE_CODE_INTERNAL_SERVER_ERROR).build();
	}
	
}
