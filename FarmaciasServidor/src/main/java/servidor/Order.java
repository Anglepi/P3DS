package servidor;
//Modelo de datos abstracto (DAO) de una Orden de compra 
import java.util.ArrayList;

public class Order {
	private int id;
	private ArrayList<Producto> productos;
	private int fk_producto;
	private int cantidad;
	private float precio;
	private int fk_farmacia;
	private int fk_usuario;
	private int id_order;
		
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(ArrayList<Producto> productos, float precio,int fk_farmacia, int fk_usuario) {
		super();
		this.productos = productos;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fk_farmacia = fk_farmacia;
		this.fk_usuario = fk_usuario;
	}
	public Order(int id, ArrayList<Producto> productos, int cantidad, float precio, int fk_farmacia, int fk_usuario) {
		super();
		this.id = id;
		this.productos = productos;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fk_farmacia = fk_farmacia;
		this.fk_usuario = fk_usuario;
	}
	
	public Order(int id, int fk_producto,  float precio, int fk_farmacia, int fk_usuario, int id_order) {
		super();
		this.id = id;
		this.fk_producto = fk_producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.fk_farmacia = fk_farmacia;
		this.fk_usuario = fk_usuario;
		this.id_order = id_order;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", precio=" + precio
				+ "]";
	}
	
	public int getId_order() {
		return id_order;
	}
	public void setId_order(int id_order) {
		this.id_order = id_order;
	}
	public int getId() {
		return id;
	}
	public int cantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getFk_producto() {
		return fk_producto;
	}
	public void setFk_producto(int fk_producto) {
		this.fk_producto = fk_producto;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Producto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getFk_farmacia() {
		return fk_farmacia;
	}
	public void setFk_farmacia(int fk_farmacia) {
		this.fk_farmacia = fk_farmacia;
	}
	public int getFk_usuario() {
		return fk_usuario;
	}
	public void setFk_usuario(int fk_usuario) {
		this.fk_usuario = fk_usuario;
	}
	
}
