package servidor;
//Modelo de datos abstracto (DAO) de un Usuario
public class Usuario {	
	private String nombre;
	private String nick;
	private String rol;
	private String password;
	private String email;
	private int ID;
	
	public Usuario() {
		super();
	}
	public Usuario(String nombre, String nick, String rol) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.rol = rol;
	}
	public Usuario(String nombre, String nick, String rol, String email) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.rol = rol;
		this.email = email;
	}
	public Usuario(String nombre, String nick, String rol, String password, String email) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.rol = rol;
		this.password = password;
		this.email = email;
	}
	public Usuario(int iD,String nombre, String nick, String rol, String password, String email) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.rol = rol;
		this.password = password;
		this.email = email;
		ID = iD;
	}
	public Usuario(int iD, String nombre, String nick, String rol, String email) {
		super();
		this.nombre = nombre;
		this.nick = nick;
		this.rol = rol;
		this.email = email;
		ID = iD;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", nick=" + nick + ", rol=" + rol + ", password=" + password + ", email="
				+ email + "]";
	}
}
