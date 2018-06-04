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
import org.apache.commons.codec.digest.DigestUtils;
import com.google.gson.Gson;
import facade.UsuarioFacade;
import servidor.Usuario;

//Parte REST del servidor
@Path("usuarios")
public class Usuarios {
    @GET
    @Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Lo tengo!";
    }
    @GET
    @Produces("application/json")
    public Response test() {
    	
    	UsuarioFacade uf = new UsuarioFacade();
    	
    	ArrayList<Usuario> usuarios= uf.getUsuarios();
    	String usuariosJSON = new Gson().toJson(usuarios);
    	
    	if(usuarios.size()>0)
    		return Response.status(200).entity("{\"status\":\"Ok\",\"mensaje\":\"Encontrados los usuarios.\",\"usuarios\":"+usuariosJSON+"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al actualizar un usuario.\"}").build();
    }
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response addUser(Usuario u) {
    	
    	UsuarioFacade uf = new UsuarioFacade();
    	
    	boolean actualizado = uf.newUsuario(u);
    	
    	if(actualizado)
    		return Response.status(201).entity("{\"status\":\"ok\"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al añadir un usuario.\"}").build();
    }
    /*@POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(Usuario u) {
    	UsuarioFacade uf = new UsuarioFacade();
    	boolean actualizado = uf.updateUsuario(u);
    	if(actualizado)
    		return Response.status(204).entity("{\"status\":\"OK\"}").build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al actualizar un usuario.\"}").build();
    }
    @Path("login")*/
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(Usuario u) {
    	UsuarioFacade uf = new UsuarioFacade();
    	System.out.println(u.toString());
    	Usuario login = uf.loginUsuario(u);
    	if(login!= null) {
    		String usuario = new Gson().toJson(login);
    		return Response.status(200).entity("{\"status\":\"OK\",\"mensaje\":\"Login con éxito.\",\"usuario\":"+usuario+"}").build();
    	}
    	else
    		return Response.status(404).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el login.\"}").build();
    }
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public Response deleteUser(Usuario u) {
    	UsuarioFacade uf = new UsuarioFacade();
    	boolean actualizado = uf.deleteUsuario(u);
    	if(actualizado)
    		return Response.status(204).build();
    	else
    		return Response.status(500).entity("{\"status\":\"Fallo\",\"mensaje\":\"Fallo en el servidor al actualizar un usuario.\"}").build();
    }
}