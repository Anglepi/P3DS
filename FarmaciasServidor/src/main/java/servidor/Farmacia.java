package servidor;
//Modelo de datos abstracto (DAO) de una Farmacia
public class Farmacia {

	private int ID;
	private String Nombre;
	private float latitud;
	private float longitud;
	public Farmacia(String nombre, float latitud, float longitud) {
		super();
		Nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Farmacia(int iD, String nombre, float latitud, float longitud) {
		super();
		ID = iD;
		Nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public Farmacia() {
		super();
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public float getLatitud() {
		return latitud;
	}
	public void setLatitud(float latitud) {
		this.latitud = latitud;
	}
	public float getLongitud() {
		return longitud;
	}
	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
}
