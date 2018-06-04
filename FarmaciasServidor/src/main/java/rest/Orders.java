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
import facade.OrderFacade;
import servidor.Farmacia;
import servidor.Order;

@Path("orders")
public class Orders {
    @GET
    @Produces("application/json")
    public Response getOrders() {
    	OrderFacade of = new OrderFacade();
    	ArrayList<Order> orders= of.getOrders();
    	String ordersJSON = new Gson().toJson(orders);
    	
    	if(orders.size()>0)
    		return Response.status(200).entity("{\"status\":\"Ok\",\"mensaje\":\"Encontradas los orders.\",\"orders\":"+ordersJSON+"}").build();
    	else
    		return Response.status(404).entity("{\"status\":\"Fallo\",\"mensaje\":\"No se han encontrado orders.\"}").build();
    }
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response addOrder(Order o) {    	
    	OrderFacade of = new OrderFacade();
    	
    	boolean actualizado = of.createOrder(o);
    	
    	if(actualizado)
    		return Response.status(201).entity("{\"status\":\"ok\"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al crear un order.\"}").build();
    }
}
