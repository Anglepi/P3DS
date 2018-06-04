package facade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import servidor.Db;
import servidor.Farmacia;
import servidor.Order;
import servidor.Producto;

public class OrderFacade {
	
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	
	PreparedStatement pstmt = null;
	
	public OrderFacade() {
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
	
	public boolean createOrder(Order o)
	{
		
		String insertarOrder = "INSERT INTO ORDERS"
				+ "(fk_producto,PRECIO,fk_farmacia,fk_usuario,ID_ORDER) VALUES"
				+ "(?,?,?,?,?)";
		
		String selectLastOrder = "SELECT ID_ORDER FROM ORDERS ORDER BY ID_ORDER DESC";
		int lastID = 0;
		
		try {
			stmt = this.con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.rs = stmt.executeQuery(selectLastOrder);
			this.rs.next();
			
			if (rs.first()) {
				lastID = rs.getInt("ID_ORDER")+1;
				  
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		System.out.println(insertarOrder);

		try {
			for ( Producto p : o.getProductos()) {
				pstmt = this.con.prepareStatement(insertarOrder);
				pstmt.setInt(1, p.getID());
				pstmt.setFloat(2, p.getPrecio());
				pstmt.setInt(3, o.getFk_farmacia());
				pstmt.setInt(4, o.getFk_usuario());
				pstmt.setInt(5, lastID);
				
				pstmt.execute();
			}

			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Order> getOrders(){
		String getOrders = "SELECT * FROM ORDERS";
		ArrayList<Order> orders= new ArrayList<Order>();
		
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getOrders);
			
			while(rs.next()) {
				orders.add(new Order(rs.getInt("ID"),rs.getInt("fk_producto"),rs.getFloat("PRECIO"),rs.getInt("fk_farmacia"),rs.getInt("fk_usuario"),rs.getInt("ID_ORDER")));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
}
