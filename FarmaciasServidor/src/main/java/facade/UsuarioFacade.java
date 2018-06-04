package facade;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import servidor.Db;
import servidor.Usuario;

public class UsuarioFacade {
	
	Statement stmt = null;
	ResultSet rs = null;
	Db database = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	public UsuarioFacade() {
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
	public boolean newUsuario(Usuario u) {
		String insertarUsuario = "INSERT INTO USER"
				+ "(NOMBRE,NICK,PASS,ROL,EMAIL) VALUES"
				+ "(?,?,?,?,?)";
		System.out.println(insertarUsuario);
		try {
			pstmt = this.con.prepareStatement(insertarUsuario);
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getNick());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getRol());
			pstmt.setString(5, u.getEmail());
			pstmt.execute();	
			this.con.close();
			return true;	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateUsuario(Usuario u) {
		String updateUser = "UPDATE USER SET nombre=?, nick=? where email=?";
		try {
			pstmt= this.con.prepareStatement(updateUser);
			pstmt.setString(1, u.getNombre());
			pstmt.setString(2, u.getNick());
			pstmt.setString(3, u.getEmail());
			pstmt.execute();
			
			this.con.close();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteUsuario(Usuario u) {
		String deleteUser = "DELETE FROM USER WHERE ID=?";
		try {
			pstmt= this.con.prepareStatement(deleteUser);
			pstmt.setInt(1, u.getID());
			pstmt.execute();
			
			this.con.close();
			return true;	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public Usuario loginUsuario(Usuario u) {
		String loginUser = "SELECT * FROM USER WHERE email='"+u.getEmail()+"'";
		try {
			stmt= this.con.createStatement();
		    ResultSet rs = stmt.executeQuery(loginUser);
		    while(rs.next())
		    {
		    	System.out.println("Contraseña en la db:"+rs.getString("PASS"));
		    	System.out.println("Contraseña introducida:"+u.getPassword());
		    	
		    	 if (rs.getString("PASS").compareTo(u.getPassword())==0)
		    	 {
		    		System.out.println("Login correcto");
		 			return new Usuario(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getString("NICK"),rs.getString("PASS"),rs.getString("ROL"),rs.getString("EMAIL"));
		 			
		    	 }else {

		 			this.con.close();
		 			return null;
		    	 }
		    }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Usuario> getUsuarios(){
		String getUsers = "SELECT ID,NOMBRE,NICK,ROL,EMAIL FROM USER";
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			stmt = this.con.createStatement();
			this.rs = stmt.executeQuery(getUsers);
			
			while(rs.next()) {
				usuarios.add(new Usuario(rs.getInt("ID"),rs.getString("NOMBRE"),rs.getString("NICK"),rs.getString("ROL"),rs.getString("EMAIL")));
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
}
