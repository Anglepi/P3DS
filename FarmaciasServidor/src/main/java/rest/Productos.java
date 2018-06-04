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
import facade.ProductoFacade;
import servidor.Farmacia;
import servidor.Producto;
@Path("productos")
public class Productos {
	@GET
	@Produces("application/json")
	public Response getProductos() {
		
		ProductoFacade pf = new ProductoFacade();
		ArrayList<Producto> productos= pf.getProductos();

    	String productosJSON = new Gson().toJson(productos);
		
		if(productos.size()>0)
    		return Response.status(200).entity("{\"status\":\"Ok\",\"mensaje\":\"Encontradas las farmacias.\",\"productos\":"+productosJSON+"}").build();
    	else
    		return Response.status(404).entity("{\"status\":\"Fallo\",\"mensaje\":\"No se han encontrado productos.\"}").build();	
	}
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response addProducto(Producto p) {
    	ProductoFacade pf = new ProductoFacade();
    	boolean actualizado = pf.newProducto(p);
    	if(actualizado)
    		return Response.status(201).entity("{\"status\":\"ok\"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al añadir un producto.\"}").build();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateProducto(Producto p) {
    	ProductoFacade pf = new ProductoFacade();
    	boolean actualizado = pf.updateProducto(p);
    	if(actualizado)
    		return Response.status(204).build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al actualizar un producto.\"}").build();
    }
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteProducto(Producto p) {
    	ProductoFacade pf = new ProductoFacade();
    	boolean actualizado = pf.deleteProducto(p);
    	if(actualizado)
    		return Response.status(204).build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al eliminar un producto.\"}").build();
    }
}
