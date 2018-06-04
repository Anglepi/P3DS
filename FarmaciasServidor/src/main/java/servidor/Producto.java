package servidor;
//Modelo de datos abstracto (DAO) de un Producto
public class Producto {
	private String nombre;
	private int cantidad;
	private float precio;
	private String imagen="IMAGEN";
	private int ID;

	public Producto(String nombre, int cantidad, float precio, String imagen, int iD) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.imagen = imagen;
		ID = iD;
	}
	public Producto(String nombre, int cantidad, float precio, String imagen) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.imagen = imagen;
	}
	public Producto(int ID) {
		this.ID = ID;
	}
	public Producto() {
		super();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", imagen=" + imagen
				+ ", ID=" + ID + "]";
	}
}
