package facade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import servidor.Db;
import servidor.Farmacia;
import servidor.Producto;
public class ProductoFacade {
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	public ProductoFacade() {
		try {
			this.database = new Db();
			this.con = this.database.getDB();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean newProducto(Producto p) {
		String insertarProducto = "INSERT INTO PRODUCTO"
				+ "(ID,NOMBRE,CANTIDAD,PRECIO,IMAGEN) VALUES "
				+ "(?,?,?,?,?)";
		System.out.println(insertarProducto);
		try {
			pstmt = this.con.prepareStatement(insertarProducto);
			pstmt.setInt(1, p.getID());
			pstmt.setString(2, p.getNombre());
			pstmt.setInt(3, p.getCantidad());
			pstmt.setFloat(4, p.getPrecio());
			pstmt.setString(5, p.getImagen());
			
			pstmt.execute();
			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public boolean updateProducto(Producto p) {
		
		String updateProducto = "UPDATE PRODUCTO SET NOMBRE=?, CANTIDAD=?, PRECIO=?, IMAGEN=? where ID=?";
		try {
			pstmt= this.con.prepareStatement(updateProducto);
			pstmt.setString(1, p.getNombre());
			pstmt.setInt(2, p.getCantidad());
			pstmt.setFloat(3, p.getPrecio());
			pstmt.setString(4, p.getImagen());
			pstmt.setInt(5, p.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteProducto(Producto p) {
		
		String deleteProducto = "DELETE FROM PRODUCTO WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteProducto);
			pstmt.setInt(1, p.getID());
			pstmt.execute();
			
			this.con.close();
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Producto> getProductos(){
		String getProductos = "SELECT * FROM PRODUCTO";
		ArrayList<Producto> productos= new ArrayList<Producto>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getProductos);
			
			while(rs.next()) {
				productos.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDAD"),rs.getFloat("PRECIO"),rs.getString("IMAGEN"),rs.getInt("ID")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}

}
