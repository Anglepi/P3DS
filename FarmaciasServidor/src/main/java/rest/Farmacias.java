package rest;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import facade.FarmaciaFacade;
import servidor.Farmacia;

@Path("farmacias")
public class Farmacias {
    @GET
    @Produces("application/json")
    public Response test() {
    	
    	FarmaciaFacade ff = new FarmaciaFacade();
    	
    	ArrayList<Farmacia> farmacias= ff.getFarmacias();
    	String farmaciasJSON = new Gson().toJson(farmacias);
    	
    	if(farmacias.size()>0)
    		return Response.status(200).entity("{\"status\":\"Ok\",\"mensaje\":\"Encontradas las farmacias.\",\"farmacias\":"+farmaciasJSON+"}").build();
    	else
    		return Response.status(404).entity("{\"status\":\"Fallo\",\"mensaje\":\"No se han encontrado farmacias.\"}").build();
    }
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response addFarmacia(Farmacia f) {
    	
    	FarmaciaFacade fc = new FarmaciaFacade();
    	
    	boolean actualizado = fc.newFarmacia(f);
    	
    	if(actualizado)
    		return Response.status(201).entity("{\"status\":\"ok\"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al añadir una Farmacia.\"}").build();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateFarmacia(Farmacia f) {
    	
    	FarmaciaFacade ff = new FarmaciaFacade();
    	
    	boolean actualizado = ff.updateFarmacia(f);
    	
    	if(actualizado)
    		return Response.status(204).build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al actualizar una farmacia.\"}").build();
    }
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteFarmacia(Farmacia f) {
    	
    	FarmaciaFacade ff = new FarmaciaFacade();
    	
    	boolean actualizado = ff.deleteFarmacia(f);
    	
    	if(actualizado)
    		return Response.status(204).build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al eliminar la farmacia.\"}").build();
    }
}
